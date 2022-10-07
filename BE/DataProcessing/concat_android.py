import re
import pandas as pd
import os

# def katalk_title(file_path):
#     title = input().split()[:-3]
#     title = '_'.join(title)
#     return katalk_title
    

def katalk_msg_parse(file_path):
    my_katalk_data = list()
    katalk_msg_pattern = "[0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},.*:"
    date_info = "[0-9]{4}년 [0-9]{1,2}월 [0-9]{1,2}일 \S요일"
    # in_out_info = "[0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2}:.*"
    in_info = "([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(초대했습니다.)"
    out_info = "([0-9]{4}[년.] [0-9]{1,2}[월.] [0-9]{1,2}[일.] 오\S [0-9]{1,2}:[0-9]{1,2},).*(나갔습니다.)"
    var = 1
    title = list()

    for line in open(file_path, 'rt', encoding='utf-8-sig'):
        if var ==1 :
            title = line.split()[:-3]
            title = '_'.join(title)
            var-=1
        
        if re.match(date_info, line) or re.match(in_info, line) or re.match(out_info, line):
        # elif re.match(date_info, line):
            continue
        elif line == '\n':
            continue
        elif re.match(katalk_msg_pattern, line):
            line = line.split(",")
            date_time = line[0]
            user_text = line[1].split(" : ", maxsplit=1)
            user_name = user_text[0].strip()
            text = user_text[1].strip()
            my_katalk_data.append({'date_time': date_time,
                                   'user_name': user_name,
                                   'text': text
                                   })

        else:
            if len(my_katalk_data) > 0:
                my_katalk_data[-1]['text'] += "\n"+line.strip()

    my_katalk_df = pd.DataFrame(my_katalk_data)

    # return my_katalk_df, title
    return my_katalk_df,title

f_path = './temp/'
file_list = os.listdir(f_path)
print(file_list)
    
room_list = list()

for f_name in file_list:
    room = katalk_msg_parse(os.path.join(f_path, f_name))[0]
    room['room_name'] = katalk_msg_parse(os.path.join(f_path, f_name))[1]
    room_list.append(room)
# print(room_list)

# files = {
#     'KakaoTalkChats_app2.txt': 'talk_1',
#     'KakaoTalkChats_app3.txt': 'talk_2',
# }
# room_list = list()

# for f_name in files:
#     room = katalk_msg_parse(os.path.join(f_path, f_name))
#     room['room_name'] = files[f_name]
#     room_list.append(room)

df = pd.concat(room_list, ignore_index=True)
# print(df)
df['date_time'] = df['date_time'].str.replace('오전', 'AM') #오전 -> AM
df['date_time'] = df['date_time'].str.replace('오후', 'PM') #오후 -> PM
df['date_time'] = pd.to_datetime(df['date_time'], format='%Y년 %m월 %d일 %p %I:%M') #dtype: datetime64[ns]으로 바꾸어주어 AM, PM 없이 0~24시로 나타내기

df['year'] = df['date_time'].dt.year
df['month'] = df['date_time'].dt.month
df['day'] = df['date_time'].dt.day
df['weekday'] = df['date_time'].dt.weekday
df['hour'] = df['date_time'].dt.hour


df.to_csv('result.csv')
df.to_csv('result.txt', sep = '\t')