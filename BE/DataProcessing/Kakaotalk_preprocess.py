import re

def main(filename):
    ## 카카오톡 파일 읽기
    f = open(filename, "r", encoding='UTF8');
    with f:
        example = f.readlines()
        print(example[0])

        nameIdx = example[0].rfind("님과 카카오톡 대화")
        dateIdx = example[1].find(":")

        # roomName = example[0][:nameIdx - 1].strip() # 채팅방 이름
        # saveDate = example[1][dateIdx + 2:].strip() # 대화 저장 날짜
        # print(roomName)
        # print(saveDate)
        print(example[4])

        for line in example:
            if(line[0] == '-'): # 날짜 구분선
                rawchatDate = line.replace("-", "")
                # print(chatDate.strip())
                # if len(chatDate) == 19:
                rawchatDate = rawchatDate.split()
                chatDate = [0]*3
                for i in range(3):
                    chatDate[i] = re.sub(r'[^0-9]','', rawchatDate[i])
                # print(line.strip())
                for i in range(1,3):
                    if len(chatDate[i]) == 1:
                        chatDate[i] = '0'+chatDate[i]
                chatDate = '-'.join(chatDate)

                continue
            elif(line[0] == '['): # 대화
                # 대화를 [시간 사용자 대화내용] 포맷으로 출력
                # print(line)
                # print(line.strip())
                line = line.strip()
                # print(line)
                personIdx = line.find(']')
                # print(line[1:timeIdx])
                timeIdx = line.find(']',personIdx+1,len(line))
                rawTime = line[personIdx + 3 :timeIdx]
                # print(rawTime)
                # print(chatDate, end = ' ')
                # print(chatDate.strip(), end=' ')
                if rawTime[1] == "전":
                    # if len(rawTime) == 7:
                    #     print(f'0{rawTime[3:]}', end=' ')
                    # elif len(rawTime) == 8:
                    #     print(f'{rawTime[3:]}', end=' ')
                    if len(rawTime) == 7:
                        rawTime = '0' + rawTime[3:]
                    elif len(rawTime) == 8:
                        rawTime = rawTime[3:]
                elif rawTime[1] == "후":
                    if len(rawTime) == 7:
                        rawTime = (str(int(rawTime[3]) + 12) + rawTime[-3:])
                    elif len(rawTime) == 8:
                        rawTime = (str(int(rawTime[4]) + 22) + rawTime[-3:])
                # print(line[1:personIdx], end=' ')
                person = line[1:personIdx]
                # print(line[timeIdx+2:])
                content = line[timeIdx+2:]
                chat = (chatDate, rawTime, person, content)
                print(*chat)


def parseDate(strDate):
    return


if __name__ == "__main__":
    filename = "KakaoTalk_20220915_1114_15_060_group.txt"
    # print(filename)
    main(filename)