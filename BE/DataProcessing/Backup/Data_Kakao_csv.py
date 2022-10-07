from konlpy.tag import Okt
from pyspark import SparkContext
from pyspark.sql import SparkSession
from zipfile import ZipFile
import os
import pandas as pd
import re
import sys
import math

# 제외 조건이 되는 info를 추가했습니다.
# in_info2, out_info2, cover_info
ktalk_msg_pattern = '[0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},.*:'
date_info = '[0-9]{4}년 [0-9]{1,2}월 [0-9]{1,2}일 \S요일'
in_info = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(초대했습니다.)'
in_info2 = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(들어왔습니다.)'
out_info = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(나갔습니다.)'
out_info2 = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(내보냈습니다.)'
cover_info = '([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(가렸습니다.)'

def split_token(line):
    token_data = []
    okt = Okt()
    if re.match(date_info, line) or re.match(in_info, line) or re.match(in_info2, line) or re.match(out_info, line) or re.match(out_info2, line) or re.match(cover_info, line) or line == '\n':
        return
    elif re.match(ktalk_msg_pattern, line):
        line = line.split(',')
        if len(line) >= 2:
            date = line[0]
            user_text = line[1].split(':', maxsplit=1)
            user_name = user_text[0].strip()
            if len(user_text) == 1:
                return
            text = user_text[1].strip()
            lstText = okt.nouns(text)
            for T in lstText:
                if T != ' ' and T not in ('사진', '이모티콘', '진짜', '오늘', '내일', '어제', '코코', '사장', '삭제', '지금', '후기'):
                    token_data.append({'date': date, 'user_name': user_name, 'text': T}) 
    else:
        if len(token_data):
            token_data[-1]['text'] += '\n'+line.strip()
    return token_data


def split_line(line):
    line_data = []
    if re.match(date_info, line) or re.match(in_info, line) or re.match(in_info2, line) or re.match(out_info, line) or re.match(out_info2, line) or re.match(cover_info, line) or line == '\n':
        return
    elif re.match(ktalk_msg_pattern, line):
        line = line.split(',')
        if len(line) >= 2:
            date = line[0]
            user_text = line[1].split(':', maxsplit=1)
            user_name = user_text[0].strip()
            if len(user_text) == 1:
                return
            text = user_text[1].strip()
            line_data.append({'date': date, 'user_name': user_name, 'text': text, 'count': 1})  
            # line_data.append({'date': date, 'user_name': user_name})  
    else:
        if len(line_data):
            line_data[-1]['text'] += '\n'+line.strip()
    return line_data
file_path = './kakaotalk/Chats.zip'
room_list = []
room_list1 = []

# pyspark 초기 세팅
if len(sys.argv) != 2:
    print("usage : python Data_Kakao_csv.py kakaotalk/Chats.zip")
else:
    spark = SparkSession.builder.master('local[*]').appName('csvToRDD').getOrCreate()
    sc = SparkContext.getOrCreate()

    with ZipFile(file_path, 'r') as zipObj:
        zip_file_list = zipObj.namelist()
        for fileName in zip_file_list:
            # zip파일 내의 확장자 txt만 확인
            if '.txt' in fileName:
                t_file = zipObj.read(fileName).decode('utf-8-sig')
                lines = list(t_file.splitlines())
                # 방 이름 가지고 오기
                roomname = lines[0].split()[:-3]
                roomname = '_'.join(roomname)
                # pyspark 적용
                n = math.ceil(sys.getsizeof(lines) / 51200)
                parallel = sc.parallelize(lines[1:], n)
                # map 함수 적용
                result = parallel.map(lambda line : split_token(line))
                result1 = parallel.map(lambda line : split_line(line))
                # result의 값을 담은 room을 room_list에 추가
                # None 값 제거
                result = result.filter(lambda el : el != None)
                result1 = result1.filter(lambda el : el != None)
                # list flatten 
                result = result.flatMap(lambda x: x).collect()
                result1 = result1.flatMap(lambda x: x).collect()
                # dataframe으로 변경
                room = pd.DataFrame(result)
                room1 = pd.DataFrame(result1)
                # 대화방 이름 추가
                room['room_name'] = roomname
                room1['room_name'] = roomname
                room_list.append(room)
                room_list1.append(room1)
        df = pd.concat(room_list, ignore_index=True)
        df1 = pd.concat(room_list1, ignore_index=True)


        df1['date'] = df1['date'].str.replace('오전', 'AM')
        df1['date'] = df1['date'].str.replace('오후', 'PM')
        df1['date'] = pd.to_datetime(df1['date'], format='%Y년 %m월 %d일 %p %I:%M')
        df1['hour'] = df1['date'].dt.hour
        df1['date'] = df1['date'].dt.strftime('%Y-%m-%d')

        df['date'] = df['date'].str.replace('오전', 'AM')
        df['date'] = df['date'].str.replace('오후', 'PM')
        df['date'] = pd.to_datetime(df['date'], format='%Y년 %m월 %d일 %p %I:%M')
        df['date'] = df['date'].dt.strftime('%Y-%m-%d')

        if not os.path.isdir('./result'):
            os.mkdir('./result')

        df.to_csv('./result/Data_Kakao_word.csv')
        df1.to_csv('./result/Data_Kakao_line.csv')
    sc.stop()