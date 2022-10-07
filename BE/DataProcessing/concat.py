import re
import pandas as pd
import os

def katalk_msg_parse(file_path):
    my_katalk_data = list()
    katalk_msg_pattern = "[0-9]{4}. [0-9]{1,2}. [0-9]{1,2}. [0-9]{1,2}:[0-9]{1,2},.*:"
    date_info = "[0-9]{4}년 [0-9]{1,2}월 [0-9]{1,2}일 \S요일"
    in_out_info = "[0-9]{4}. [0-9]{1,2}. [0-9]{1,2}. [0-9]{1,2}:[0-9]{1,2}:.*"
    var = 1
    # title = list()

    for line in open(file_path, 'rt', encoding='utf-8-sig'):
        if var ==1 :
            # title = line[:-4]
            var-=1
        
        elif re.match(date_info, line) or re.match(in_out_info, line):
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

    return my_katalk_df

f_path = ''
files = {
    'Talk_2022.9.16 21_29-2.txt': 'talk_1',
    'Talk_2022.9.16 21_29-1.txt': 'talk_2',
    'Talk_2022.9.16 14_46-1.txt': 'talk_3',
    'Talk_2022.9.15 09_14-1.txt': 'talk_4',
    'Talk_2022.9.14 15_03-1.txt': 'talk_5',
    'Talk_2022.9.14 10_22-1.txt': 'talk_6',
}

room_list = list()

for f_name in files:
    room = katalk_msg_parse(os.path.join(f_path, f_name))
    room['room_name'] = files[f_name]
    room_list.append(room)

df = pd.concat(room_list, ignore_index=True)
df['date_time'] = pd.to_datetime(df['date_time'], format='%Y. %m. %d. %H:%M')

df['year'] = df['date_time'].dt.year
df['month'] = df['date_time'].dt.month
df['day'] = df['date_time'].dt.day
df['weekday'] = df['date_time'].dt.weekday
df['hour'] = df['date_time'].dt.hour


df.to_csv('result.csv')
df.to_csv('result.txt', sep = '\t')