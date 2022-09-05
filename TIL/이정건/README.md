[TOC]



### 0905

###### Tacademy Hadoop 1강

하둡 - 비정형 데이터를 포함한 빅데이터를 다루기 위한 가장 적절한 플랫폼

Lucene(인덱싱 라이브러리) ->(아들 프로젝트, 2002) Nutch ->(아들 프로젝트) Hadoop

Hadoop 개발의 배경

1. Nutch를 사용할 때, 분산병렬처리를 통해 데이터를 색인하는 과정의 어려움
2. GFS(Google File System)이 release된다(2003) -> Hadoop Distributed File System
3. Map Reduce(구글에서 분산병렬처리를 하는 방법) 알고리즘이 공개된다(2004) -> Hadoop MapReduce
4. Nutch의 sub-project로 Hadoop이 개발되기 시작(2006)
5. Hadoop을 지원하던 Yahoo에서 1000 node에 Hadoop을 사용하기 시작(2007)
6. Apache의 top-level project가 된다(2008), 이후 슈퍼컴퓨터의 성능을 뛰어넘다
7. 1.0버젼 발매(2011)
8. 2.0버젼 발매(YARN 포함)(2012)
9. 3.0버젼 발매(2017)

HADOOP 생태계

- Core Hadoop
- HBase(NoSQL) - 분산데이터베이스
- Pig - Script Language
- Zookeeper -  
- Mahout -  SQL로 하기 용이하지 않은 머신러닝등의 알고리즘을 하둡같은 분산데이터베이스에서 처리할 수 있게 한다
- Hive - SQL로 Hadoop에 있는 데이터를 다루게 한다
- Avro
- Whirr
- Sqoop - 관계형데이터베이스(MariaDB 등)과 Hadoop간 데이터 주고받는 것을 용이하게 해주는 프레임워크
- HCatalog - 하둡에서 저장되어 관리하는 스키마를 하나의 카탈로그 서비스에서 관리
- Mrunit - MapReducing Test Framework
- Bigtop
- Oozie - 하둡에 저장된 데이터를 처리하여 데이터마트를 만드는 과정의 workflow를 스케쥴링하는 스케쥴러
- ....

![image-20220902112730578](README.assets/image-20220902112730578.png)

![image-20220902112844854](README.assets/image-20220902112844854.png)

![image-20220902112915581](README.assets/image-20220902112915581.png)

하둡 -> 본래는 많은 데이터를 저장하는데 비용을 아끼는데 의의 -> Data로부터 새로운 Insight와 사업기회를 찾기 위한 노력과 시장확대

컴퓨터, 통신의 소형화, IoT로 많은 센서들이 생기고, 데이터가 계속 생겨난다