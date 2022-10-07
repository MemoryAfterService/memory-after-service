import sys
sys.stdin = open('SMS_input.txt', 'rt', encoding='UTF8')



# print(sys.getdefaultencoding())
# print(a)
while 1:
    try:
        a = input()
        if a == '[Web발신]':
            continue
        else:
            a = a.split()
            leng = len(a)
            op = []
            a[4] = list(a[4])
            # print(a[4])
            # print(a)
            # print(a[4][i].isdigit())
            for i in range(5,leng):
                op.append(a[i])
            for i in range(len(a[4])-1,-1,-1):
                if a[4][i].isdigit() or a[4][i] == ',':
                    continue
                else:
                    del a[4][i]
            a[4] = ''.join(a[4])
            op.append(a[4])
            op.append(a[2])
            op.append(a[3])
            print(*op)
        
    except:
        break