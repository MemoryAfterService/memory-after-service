import pandas as pd
import re
from zipfile import ZipFile

def ktalk_msg_parse(lines):
    my_ktalk_data = []
    ktalk_msg_pattern = '[0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},.*:'
    date_info = '[0-9]{4}년 [0-9]{1,2}월 [0-9]{1,2}일 \S요일'
    in_info = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(초대했습니다.)'
    out_info = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(나갔습니다.)'
    roomname = []

    for idx, line in enumerate(lines):
        # 첫줄에서 roomname을 받아 저장
        if idx == 0:
            roomname = line.split()[:-3]
            roomname = '_'.join(roomname)
        # 제외되는 조건인 경우
        if re.match(date_info, line) or re.match(in_info, line) or re.match(out_info, line) or line == '\n':
            continue
        # ktalk_msg_pattern에 맞는 경우
        elif re.match(ktalk_msg_pattern, line):
            line = line.split(',')
            date_time = line[0]
            user_text = line[1].split(':', maxsplit=1)
            user_name = user_text[0].strip()
            text = user_text[1].strip()
            my_ktalk_data.append({'date_time': date_time, 'user_name': user_name, 'text': text})
        # 엔터로 구분된 경우
        else:
            if len(my_ktalk_data):
                my_ktalk_data[-1]['text'] += '\n'+line.strip()
    # pandas의 DataFrame로 테이블형태로 저장
    my_ktalk_df = pd.DataFrame(my_ktalk_data)

    return my_ktalk_df, roomname

# 카카오톡 대화(Chats.zip)의 위치는 kakaotalk 폴더
file_path = './kakaotalk/Chats.zip'
room_list = []

# ZipFile을 활용하여 zip파일 읽음
with ZipFile(file_path, 'r') as zipObj:
    zip_file_list = zipObj.namelist()
    for fileName in zip_file_list:
        # zip파일 내의 확장자 txt만 확인
        if '.txt' in fileName:
            t_file = zipObj.read(fileName).decode('utf-8')
            lines = list(t_file.splitlines())
            # 함수 ktalk_msg_parse의 return값 result 변수에 할당
            result = ktalk_msg_parse(lines)
            # result의 값을 담은 room을 room_list에 추가
            room = result[0]
            room['room_name'] = result[1]
            room_list.append(room)
    df = pd.concat(room_list, ignore_index=True)

    df['date_time'] = df['date_time'].str.replace('오전', 'AM')
    df['date_time'] = df['date_time'].str.replace('오후', 'PM')
    df['date_time'] = pd.to_datetime(df['date_time'], format='%Y년 %m월 %d일 %p %I:%M')

    df['year'] = df['date_time'].dt.year
    df['month'] = df['date_time'].dt.month
    df['day'] = df['date_time'].dt.day
    df['weekday'] = df['date_time'].dt.weekday
    df['hour'] = df['date_time'].dt.hour

    # 현재 폴더에 결과 파일 result.csv와 result.txt 저장
    df.to_csv('result.csv')
    df.to_csv('result.txt', sep = '\t')