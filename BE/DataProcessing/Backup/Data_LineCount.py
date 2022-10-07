from pyspark import SparkContext
from pyspark.sql import SparkSession
import pandas as pd
import sys

def group(line):
    words = line.split(',')
    if(words[1] == 'date'):
        return
    
    hour = words[6]

    return (hour, 1)

def rearrangement(line):
    hour = line[0]
    count = line[1]

    return hour, count

# pyspark 초기 세팅
if len(sys.argv) != 2:
    print("usage : python Data_LineCount.py result/Data_Kakao_line.csv")
else:
    spark = SparkSession.builder.master('local[*]').appName('csvToRDD').getOrCreate()
    sc = SparkContext.getOrCreate()

    source = sc.textFile("result/Data_Kakao_line.csv")

    result = source.map(lambda line : group(line))
    result = result.filter(lambda x : x != None)
    result = result.reduceByKey(lambda x, y: x + y)
    result = result.map(lambda line: rearrangement(line))

    df = pd.DataFrame(result.collect())
    df.rename(columns = {0:'Hour', 1:'Count'},inplace=True)
    df = df.sort_values(by=['Count'], ascending=[False])

    df.to_csv('./result/Data_LineCount_result.csv')
    sc.stop()