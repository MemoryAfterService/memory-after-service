from datetime import datetime
import pandas as pd
import re

def main(filename):
    result_list = []
    ## 카카오톡 파일 읽기
    f = open(filename + ".txt", "r", encoding='UTF-8');
    with f:
        text = f.readlines()

        nameIdx = text[0].rfind("님과 카카오톡 대화")
        dateIdx = text[1].find(":")

        roomName = text[0][:nameIdx - 1].strip() # 채팅방 이름
        saveDate = text[1][dateIdx + 2:].strip() # 대화 저장 날짜
        #print(roomName)
        #print(saveDate)

        for line in text:
            curDate = ""
            if(line[0] == '-'): # 날짜 구분선
                rawDate = line.replace("-", "").strip() # 2022년 8월 18일 목요일
                chatDate = ' '.join(rawDate.split()[:3]) # 2022년 8월 18일
            elif(line[0] == '['): # 대화
                # 대화를 [시간 사용자 대화내용] 포맷으로 출력
                line = line.strip()
                personIdx = line.find(']')
                timeIdx = line.find(']', personIdx + 1)
                rawTime = line[personIdx + 3:timeIdx] # 오후 4:57

                #chatDateTime = str(conversionDateTime(chatDate, rawTime))
                person = line[1:personIdx]
                content = line[timeIdx + 2:]

                #print(" ".join([chatDateTime, person, content]))
                result_list.append(" ".join([chatDateTime, person, content])+"\n")

    with open(filename + "_result.txt", "w", encoding="UTF-8") as f:
        f.writelines(result_list)
    print("SUCCESS")

def conversionDateTime(strDate, strTime):
    strTime = strTime.replace("오전", "AM")
    strTime = strTime.replace("오후", "PM")

    stringDateTime = "\t".join([strDate, strTime]) # 2022년 8월 18일 PM 11:51
    result = datetime.strptime(stringDateTime, "%Y년 %m월 %d일 %p %I:%M") # 2022-08-18 23:51:00
    
    return result


if __name__ == "__main__":
    filename = "kakao/KakaoTalk_20220915_1114_15_060_group"
    main(filename)