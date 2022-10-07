from pyspark import SparkContext
from pyspark.sql import SparkSession
import pandas as pd

def group(line):
    words = line.split(',')
    if(words[1] == 'date'):
        return
    
    date = words[1]
    name = words[2]
    word = words[3]
    room_name = words[4]

    return ((date, name, room_name, word), 1)

def rearrangement(line):
    date = line[0][0]
    name = line[0][1]
    room_name = line[0][2]
    word = line[0][3]
    count = line[1]

    return date, name, room_name, word, count

# pyspark 초기 세팅
spark = SparkSession.builder.master('local[*]').appName('csvToRDD').getOrCreate()
sc = SparkContext.getOrCreate()

source = sc.textFile("result/Data_Kakao_word.csv")

result = source.map(lambda line : group(line))
result = result.filter(lambda x : x != None)
result = result.reduceByKey(lambda x, y: x + y)
result = result.map(lambda line: rearrangement(line))

df = pd.DataFrame(result.collect())
df.rename(columns = {0:'Date', 1:'Username', 2:'Roomname', 3:'Word', 4:'Count'},inplace=True)
df = df.sort_values(by=['Date','Count'], ascending=[True, False])

df.to_csv('./result/Data_WordCount_result.csv')
sc.stop()