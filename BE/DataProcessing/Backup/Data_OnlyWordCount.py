from pyspark import SparkContext
from pyspark.sql import SparkSession
import pandas as pd

def group(line):
    words = line.split(',')
    if(words[1] == 'date'):
        return
    
    word = words[3]

    return (word, 1)


# pyspark 초기 세팅
spark = SparkSession.builder.master('local[*]').appName('csvToRDD').getOrCreate()
sc = SparkContext.getOrCreate()

source = sc.textFile("result/Data_Kakao_word.csv")

result = source.map(lambda line : group(line))
result = result.filter(lambda x : x != None)
result = result.reduceByKey(lambda x, y: x + y)

df = pd.DataFrame(result.collect())
df.rename(columns = {0:'Word', 1:'Count'},inplace=True)
df = df.sort_values(by=['Count'], ascending=[False])

df.to_csv('./result/Data_OnlyWordCount_result.csv')
sc.stop()