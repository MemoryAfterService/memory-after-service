### 프로젝트 개요

---

병렬 분산 시스템과 맵 리듀스 프레임워크를 이해하고, 하둡을 이용하여 여러가지 빅데이터 분석문제들에 대해서 맵 리듀스 알고리즘을 자바 언어로 구현하고 실행.

### 프로젝트 목표

---

- 병렬 분산 알고리즘 구현이 가능한 맵리듀스 프레임워크를 이해.
- 맵 리듀스 프레임워크를 사용할 수 있는 Hadoop 설치 및 맵 리듀스 알고리즘 코드를 실행.
- 하둡을 이용하여 빅데이터 분석 및 처리용 맵 리듀스 알고리즘을 구현하는데 필요한 지식과 코딩 능력을 배양.

### Scaling-out, Scaling-up

---

- Scale-out : 아주 많은 저렴한 서버 사용
- Scale-up : 적은 수의 고성능 서버 사용
- data-intensive한 분야에서는 scaling-out 분야를 선호함
- 고성능 서버들을 가격 관점에서 선형으로 성능이 증가하지 않음

> Scaling-out이 Scaling-up 보다 낫다.
> 

### MapReduce

---

- Data-intensive Programming
    - 한대의 컴퓨터의 능력으로 처리가 어려움
    - 많은 수의 컴퓨터를 병렬적으로 처리해야함
    - MapReduce 프레임워크가 하는 일이 분배
- 맵 리듀스는 빅데이터를 이용한 효율적인 계산이 가능한 첫 번째 프로그래밍 모델
    - 기존 다른 병렬 컴퓨팅 방식은 프로그래머가 낮은 레벨의 시스템까지 잘 알고 시간을 많이 써야 함.

### MapReduce Framework (1)

---

- 빅데이터 응용분야에서 최근 주목 받고 있음
- 저렴한 컴퓨터들을 모아서 클러스터를 만들고, 빅데이터를 처리하기 위한 Scalable 병렬 소프트웨어의 구현을 쉽게 할 수 있도록 도와주는 간단한 프로그래밍 모델
- 구글의 MapReduce 혹은 오픈소스인 Hadoop 사용
- 드라이버에 해당하는 메인 함수가 map 함수와 reduce 함수를 호출하여 처리

### MapReduce Programming Model

---

- 함수형 프로그래밍 언어의 형태
- 유저는 아래 세가지 함수를 구현하여 제공해야 함
    - Main 함수
    - Map 함수 : (key1, val1) → [(key2, val2)]
    - Reduce 함수 : (key2, [val2]) → [(key3, val3)]
    
    > [ ] : 하나가 아닌 0개 이상이라는 의미, 리스트라는 의미
    > 

### MapReduce Framework (2)

---

- 맵 리듀스 프레임워크에서는 각각의 record와 tuple은 key-value 쌍으로 표시
- 맵 리듀스 프레임워크는 메인 함수를 한개의 마스터 머신에서 수행하는데, 이 머신은 Map 함수를 수행하기 전 전처리를 하거나 Reduce 함수의 후처리를 하는데 사용 가능
- 연산은 Map과 Reduce라는 사용자 정의 함수 한 쌍으로 이루어진 맵 리듀스 페이즈를 한번 수행하거나 여러번 반복해서 수행 가능
- 한번의 맵 리듀스 페이즈는 Map → (combine) → Reduce 순서로 진행

### MapReduce Phase

---

1. Map phase
    - 제일 먼저 수행되며, 데이터의 여러 파티션에 병렬 분산으로 호출되어 수행.
    - 각 머신마다 수행된 Mapper는 맵 함수가 입력 데이터의 한 줄 마다 Map 함수 호출
    - Map 함수는 (key, value) 형태로 결과를 출력하고, 여러 머신에 나눠 보내며 같은 key를 가진 쌍은 같은 머신으로 보내진다.
2. Shuffling phase
    - 모든 머신에서 맵 페이즈가 끝나면 시작.
    - 맵 페이즈에서 각각의 머신으로 보내진 (key, value) 쌍을 key를 이용하여 정렬한 후에 각각의 key 마다 같은 key를 가진 쌍을 모아서 value-list를 만든 다음, (key, value-list) 형태로 key에 따라 여러 머신에 분산하여 보냄.
3. Reduce phase
    - 모든 머신에서 셔플링 페이즈가 끝나면, 각 머신마다 리듀스 페이즈가 시작.
    - 각각의 머신에서는 셔플링 페이즈에서 해당 머신으로 보내진 (key, value-list) 마다 리듀스 함수가 호출되며, 하나가 끝나면 다음 리듀스 함수가 호출.
    - 출력이 있다면 (key, value) 형태로 출력.

### Hadoop

---

- Apache 프로젝트의 오픈소스 맵 리듀스 프레임워크
- 하둡 분산 파일 시스템 (Hadoop Distibuted File System : HDFS)
    - 빅데이터 파일을 여러 컴퓨터에 나눠서 저장.
    - 각 파일은 여러개의 순차적인 블록으로 저장.
    - 한 파일의 각각의 블록은 fault-tolerance를 위해 여러개로 복사되어 여러 머신에 나눠 저장.
    
    <aside>
    ❓ fault-tolerance
    
    ---
    
    시스템의 일부 부품에서 이상이 발생하여도 정상적 혹은 부분적으로 기능을 수행 할 수 있는 것
    
    </aside>
    
- 빅 데이터를 수천 대의 값 싼 컴퓨터에 병렬 처리하기 위해 분산.
- 주요 구성 요소들
    - MapReduce : 소프트웨어의 수행을 분산
    - HDFS : 데이터를 분산
- 하나의 Namenode(master)와 여러개의 Datanode (slaves)
    - Namenode : 파일 시스템을 관리하고 클라이언트가 파일에 접근할 수 있게 함.
    - Datanode : 컴퓨터에 들어있는 데이터를 접근할 수 있게 함.
- Java 프로그래밍 언어로 맵 리듀스 알고리즘 구현

### MapReduce의 함수

---

- Map
    - mapreduce 패키지 내의 Mapper 클래스를 상속 받아 맵 메소드를 수정
    - 입력 텍스트 파일에서 라인 단위로 호출되고 입력은 (key, value-list)의 형태
    - key는 입력 텍스트 파일에서 맨 앞 문자를 기준으로 맵 함수가 호출된 라인의 첫번째 문자까지 오프셋
    - value는 텍스트의 해당 라인 전체가 들어있음
- Reduce
    - mapreduce 패키지 내의 Reducer 클래스를 상속 받아 리듀스 메소드를 수정
    - 셔플링 페이즈의 출력을 입력으로 받는데 (key, value-list)의 형태
    - value-list는 맵 함수의 출력에서 같은 key를 갖는 (key, value) 쌍의 value 리스트
- Combine
    - 리듀스와 유사한 함수이나, 각 머신에서 맵 함수의 출력 크기를 줄여줘서 셔플링 페이즈와 리듀스 페이즈의 비용을 줄이는데 사용된다.

### MapReduce를 이용한 Word Counting 알고리즘

---

- 두개의 머신 $M_1$, $M_2$가 있고, 각 문서는 한 라인만 있다고 가정.
- 머신 $M_i$마다 mapper가 하나씩 수행되고, mapper는 map 함수를 각 라인 하나마다 차례대로 호출함.

1. 텍스트 문서의 각 라인마다 map 함수가 호출되고, 문서를 스캔하면서, 각 단어를 key로 하고, 값 1을 value로 하여 (key, value) 쌍을 출력한다.
2. 출력한 (key, value) 쌍의 key에 따라서 해시 함수를 이용하여 여러 머신에 분산 시켜 보낸다.
3. 각 key마다 그 key를 가진 value들을 모아서 value-list를 만들어 (key, value-list) 형태를 출력

<aside>
❓ MapReduce 알고리즘 예제에 사용하는 설명들

---

원래는 map 함수의 출력을 key로 구분하여 각각의 머신에 분산하지만, 편의상 이유로 한 머신에서 정리하는 것으로 설명한다.

</aside>

### Combine 함수

---

- Map 함수의 결과 크기를 줄여줌.
- 각각의 머신에서 Reduce 함수를 이용하는 것처럼 수행됨
- 셔플링 비용을 줄여줌 → 맵 리듀스 알고리즘 디자인에서 사용하는 것이 좋음
- 예제 알고리즘에서는 설명의 편의를 위해 사용 X

### Overview

---

- Mapper and Reducer
    - 각 머신에서 독립적으로 수행됨
    - Mapper는 Map 함수, Reducer는 Reduce 함수를 각각 수행
- Combine functions
    - 각 머신에서 Map 함수가 끝난 다음에 Reduce 함수가 하는 일을 부분적으로 수행
    - 셔플링 비용과 트래픽을 감소
- Mapeer와 Reducer는 필요하다면 setup() and cleanup()을 수행할 수 있다.
    - setup() : 첫 Map 함수나 Reduce 함수가 호출 되기 전 가장 먼저 수행.
        - 모든 Map 함수에 Broadcast 해서 전달해야 할 파라미터 정보를 Main에서 받아옴
        - 모든 Map 함수들이 공유하는 자료구조를 초기화 할 때 사용
    - cleanup() : 마지막 Map함수나 Reduce 함수가 끝나면 수행.
        - 모든 Map 함수들이 공유하는 자료구조의 결과를 출력하는 데 사용
    - 한 개의 MapReduce 과정을 수행 할 때, Map 페이즈만 수행하고 중단 할 수 도 있다.

### Hadoop 직접 실행 시 주의해야할 점

---

- 다시 실행하기 위해선 결과물 폴더를 삭제해야한다.
- 실행 명령어 예시
    
    ```bash
    # 빌드 명령어
    ant
    # 만약 결과 폴더가 있다면 삭제
    hdfs dfs -rm -r [결과물 폴더 경로]
    # 자바 파일 실행
    hadoop jar [자바 실행 파일 (.jar)] [실행 함수 이름] [input 경로] [output 경로]
    # 결과 파일 위치 확인
    hdfs dfs -ls [output 경로]
    # 결과 파일 내용 확인
    hdfs dfs -cat [파일 경로] | more
    ```
    # 빅데이터 분산 사전학습 (2)

## Partitioner Class 변경

### Partitioner Class

---

- Map 함수의 출력인 (key, value) 쌍이 key에 의해서 어느 Reducer로 보내질 것인지 정해지는데, 이러한 결정을 정의하는 Class
- 하둡의 기본 타입은 Hash 함수가 Default로 제공되고 있어서 key에 대한 해시 값에 따라 어느 reducer로 보낼지 결정한다.
    
    <aside>
    ❓ **하둡의 기본 타입**
    
    ---
    
    Text : String
    
    IntWritable : int
    
    LongWritable : long
    
    FloatWritable : float
    
    DoubleWritable : double
    
    </aside>
    

### MyPartitioner for IntWritable - Coding

---

- Map 함수의 출력인 (key, value)의 key가 IntWritable이고, value가 Text 타입일 때, Partitioner를 수정하여 아래와 같이 각 reducer에 가게 하려면 Partitioner Class를 수정해야 함.
- Partitioner를 수정하여 KEY가 1~30이면 Reducer 1로, 나머지는 Reducer 2로 보낸다.
    
    ![스크린샷 2022-08-24 오후 2.43.11.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c8a8f0ec-bdf5-4944-8acb-7698511e295f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-08-24_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_2.43.11.png)
    
    ```java
    import org.apache.jadoop.mapreduce.Partitioner;
    
    public static class MyPartitioner extends Partitioner<IntWritable,Text> {
    	@Override
    	public int getPartition(IntWritable key, Text value, int numPartitions){
    		/*
    			꽤 별로인 하드코드로서 직접 해보지 말것
    			간단한 이해를 위한 코드
    		*/
    		int nbOccurences = key.get();
    		
    		// nbOccurences가 30 이하면 0번 머신, 이상이면 1번 머신으로 이동
    		return nbOccurences <= 30 : 0 ? 1;
    	}
    }
    ```
    
    - 상단 코드를 작성 한후, Main 함수에 다음을 추가한다
        
        ```java
        job.setPartitionerClass(MyPartitioner.class);
        ```
        

### MyPartitioner Class

---

- Reducer 개수를 2개로 설정
- 각 단어의 첫번째 글자가 아스키 코드 순서로 a보다 앞에오면 reducer 0, 아니면 reducer 1로 가도록 설정

## Inverted Index 생성

### An Example of an Inverted Index

---

![스크린샷 2022-08-24 오후 3.23.48.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4c2d1f9-31eb-422b-86fe-5671e310f48f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-08-24_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.23.48.png)

- 맵 함수의 (key, value) 값이 단어와 위치를 표현하는 것
- key → 문서이름 : 단어의 위치

---

- Wordcount.java를 수정하여 inverted index를 생성하는 코드 작성

## Matrix Addition 연산

### Matrix Addition

---

$\begin{pmatrix}
a_{11} & a_{12} \\
a_{21} & a_{22} \\
\end{pmatrix} + 
\begin{pmatrix}
b_{11} & b_{12} \\
b_{21} & b_{22} \\
\end{pmatrix} = 
\begin{pmatrix}
a_{11} + b_{11} & a_{12} + b_{12} \\
a_{21} + b_{21} & a_{22} + b_{22} \\
\end{pmatrix}$

<aside>
❓ 입력파일

---

- 형식 : 행렬 이름<tab>행번호<tab>열번호<tab>원소값
- 예
A    0    0    3
A    0    1    -5
A    1    0    6
A    1    1    12
B    0    0    2
B    0    1    11
B    1    0    1
B    1    1    -7
</aside>

<aside>
💡 출력파일

---

- 형식 : 행번호<tab>열번호<tab>원소값
- 예
0    0    5
0    1    5
1    0    6
1    1    7
</aside>

# 빅데이터 분산 사전학습 (3)

## Matrix Multiplication 연산

### Matrix Multiplication

---

$\begin{pmatrix}
a_{11} & a_{12} \\
a_{21} & a_{22} \\
\end{pmatrix} \times 
\begin{pmatrix}
b_{11} & b_{12} \\
b_{21} & b_{22} \\
\end{pmatrix} = 
\begin{pmatrix}
c_{11} & c_{12}\\
c_{21} & c_{22}\\
\end{pmatrix}$ 

$c_{11} = (a_{11} \times b_{11}) + (a_{12} \times b_{21})$ 

- 자세한 설명은 생략한다.

### 1-Phase Matrix Multiplication

---

- C 행렬 i행 j열 원소 계싼에 필요한 쌍 = (A 행렬의 i행 p열 원소) * (B 행렬의 p행 j열 원소)
- A 행렬 i행 p열
    - key : (i, 1), (i, 2), … (i, m)
    - value : (p, $a_{ip}$)
- B 행렬 p행 j열
    - key : (1, j), (2, j), … (n, j)
    - value : (p, $b_{pj}$)

## 1-Phase Matrix Multiplication : 실전

### Map 함수

---

<aside>
💡 $A = \begin{pmatrix}
3 & -5 \\
6 & 12 \\
\end{pmatrix}$

---

- A    0    0    3
    - key : (0, 0), value : (0, 3)
    - key : (0, 1), value : (0, 3)
- A    0    1    -5
    - key : (0, 0), value : (1, -5)
    - key : (0, 1), value : (1, -5)
- A    1    0    6
    - key : (1, 0), value : (0, 6)
    - key : (1, 1), value : (0, 6)
- A    1    1    12
    - key : (1, 0), value : (1, 12)
    - key : (1, 1), value : (1, 12)
</aside>

<aside>
💡 $B = \begin{pmatrix}
2 & 11 \\
1 & -7 \\
\end{pmatrix}$

---

- B    0    0    2
    - key : (0, 0), value : (0, 2)
    - key : (1, 0), value : (0, 2)
- B    0    1    11
    - key : (0, 1), value : (0, 11)
    - key : (1, 1), value : (0, 11)
- B    1    0    1
    - key : (0, 0), value : (1, 1)
    - key : (1, 1), value : (1, 1)
- B    1    1    7
    - key : (0, 1), value : (1, -7)
    - key : (1, 1), value : (1, -7)
</aside>

### Reduce 함수

---

| key | value | result |
| --- | --- | --- |
| (0, 0) | <(0, 3), (1, -5), (0, 2), (1, 1)> | ((0, 0), 1) |
| (0, 1) | <(0, 3), (1, -5), (0, 11), (1, -7)> | ((0, 1), 68) |
| (1, 0) | <(0, 6), (1, 12), (0, 2), (1, 1)> |  |
| (1, 1) | <(0, 6), (1, 12), (0, 11), (1, -7)> | ((1, 1), -18) |
- 각 쌍의 첫번째 원소를 기준으로 서로 곱한 후 더해준다.
- Reduce 함수마다 Value-List가 메인 메모리에 다 들어갈 수 있어야 함

## 2-Phase Matrix Multiplication

### 2-Phase Matrix Multiplication : Phase 1

---

- C 행렬 i행 j열 원소 계싼에 필요한 쌍 = (A 행렬의 i행 p열 원소) * (B 행렬의 p행 j열 원소)
- A 행렬 i행 p열
    - key : (i, 1, p), (i, 2, p), … (i, m, p)
    - value : ($a_{ip}$)
- B 행렬 p행 j열
    - key : (1, j, p), (2, j, p), … (n, j, p)
    - value : ($b_{pj}$)

### Map 함수 : Phase 1

---

<aside>
💡 $A = \begin{pmatrix}
3 & -5 \\
6 & 12 \\
\end{pmatrix}$

---

- A    0    0    3
    - key : (0, 0, 0), value : (3)
    - key : (0, 1, 0), value : (3)
- A    0    1    -5
    - key : (0, 0, 1), value : (-5)
    - key : (0, 1, 1), value : (-5)
- A    1    0    6
    - key : (1, 0, 0), value : (6)
    - key : (1, 1, 0), value : (6)
- A    1    1    12
    - key : (1, 0, 1), value : (2)
    - key : (1, 1, 1), value : (12)
</aside>

<aside>
💡 $B = \begin{pmatrix}
2 & 11 \\
1 & -7 \\
\end{pmatrix}$

---

- B    0    0    2
    - key : (0, 0, 0), value : (2)
    - key : (0, 1, 0), value : (2)
- B    0    1    11
    - key : (0, 0, 1), value : (11)
    - key : (0, 1, 1), value : (11)
- B    1    0    1
    - key : (1, 0, 0), value : (1)
    - key : (1, 1, 0), value : (1)
- B    1    1    7
    - key : (1, 0, 1), value : (-7)
    - key : (1, 1, 1), value : (-7)
</aside>

### Reduce 함수 : Phase 1

---

| key | value | result |
| --- | --- | --- |
| (0, 0, 0) | <3, 2> | ((0, 0) , 6) |
| (0, 0, 1) | <-5, 1> | ((0, 0) , -5) |
| (0, 1, 0) | <3, 11> | ((0, 1) , 33) |
| (0, 1, 1) | <-5, -7> | ((0, 1) , 35) |
| (1, 0, 0) | <6, 2> | ((1, 0) , 12) |
| (1, 0, 1) | <12, 1> | ((1, 0) , 12) |
| (1, 1, 0) | <6, 11> | ((1, 1) , 66) |
| (1, 1, 1) | <12, -7> | ((1, 1) , -84) |

### Map 함수 : Phase 2

---

- Phase 1에서 받은 result를 분리해서 다시 key와 value로 나눈다

> INPUT : ((0, 0), 6) → KEY : (0, 0), VALUE : 6
> 

### Reduce 함수 : Phase 2

---

| key | value | result |
| --- | --- | --- |
| (0, 0) | <6, -5> | ((0, 0), 1) |
| (0, 1) | <33, 35> | ((0, 1), 68) |
| (1, 0) | <12, 12> | ((1, 0), 24) |
| (1, 1) | <66, -84> | ((1, 1), -18) |

### Phase 1 vs Phase 2

## All Pair Partition Algorithm

### Theta Join

---

- 조인 조건 (join-predicate)에 기본 비교연산자(<, >, ≤, ≥, ≠, =)를 사용한다
    
    ```sql
    SELECT * FROM R, S WHERE R.A > S.A;
    ```
    
    - 두 테이블 간의 모든 튜플 쌍에 대하여 조인 칼럼 값이 일치하면 두 쌍을 출력

### All Pair Partition Algorithm

모든 쌍 분할 알고리즘

---

- 테이블 R과 S에 대하여  |R| * |S| 튜플 쌍을 모두 고려함
    - R과 S를 각각 u개 파티션과 v개 파티션으로 분할함
    - |R| * |S| 튜플(레코드) 쌍을 u*v개의 disjoint한 파티션으로 분할함
        - |R|은 R에 들어있는 튜플 개수
        - |S|는 S에 들어있는 튜플 개수
    - 각각의 파티션을 한 개의 reduce 함수로 처리함
- 장점
    - 어떤 조인 조건도 처리 가능
    - 모든 reduce 함수에 들어가는 입력 사이즈가 다 비슷함
- 단점
    - 모든 튜플 쌍을 다 검사해야함
    - 각각의 reduce 함수의 출력 사이즈가 많이 다를 수 있음
- R을 2개, S를 3개로 나눠서 처리하면 모든 파티션 쌍은 다음과 같다.
    - Partition(i, j)는 R의 i번째 파티션과 S의 j번째 파티션의 튜플들이 수집된다.
    
    ![스크린샷 2022-08-26 오후 3.55.43.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2d12698b-eb59-4bae-86b9-af862d84eee7/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-08-26_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.55.43.png)
    
- 모든 파티션에 대하여 복사하여 분배함으로서 모든 경우의 수가 있도록 배치.

## All Pair Partition : Equi-Join 예제

### All Pair Partition : Equi-Join

---

- 각 테이블 R과 S에 대하여 각 파티션의 원소들은 상대 테이블의 파티션의 개수만큼 복사를 해야한다.
- 사진은 강의 자료 참고
- 선행학습 → 분할 | 과제 → equi-join
- 각 과정들
    
    ![Equi-join의 전체적인 과정을 예시로 표현한 모습](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ce6be01e-cb36-4495-9219-4909fe300d27/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-09-02_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_2.08.41.png)
    
    Equi-join의 전체적인 과정을 예시로 표현한 모습
    
    1. R과 S에서 반대편 만큼의 모든 조합을 만든다 (map phase)
    2. 같은 키끼리 셔플하여 정렬한다. (shuffling)
    3. 그 후 R과 S의 키 들을 확인하며 같은 쌍이 존재할 경우는 출력한다. (reduce phase)

## 셀프-조인을 위한 모든 쌍 분할 알고리즘

### 셀프-조인을 위한 모든 쌍 분할 알고리즘

---

- Self-Join은 한 개의 입력 테이블 D에 들어있는 레코드들 간의 조인을 말함
- 입력 테이블에 있는 레코드들을 m개의 파티션으로 나눔
    - $D_1$, $D_2$, … $D_m$ : m개의 레코드 그룹들
- Map Function
    - $D_i$에 있는 각각의 튜플 p에 대하여 아래와 같은 (key, value) 쌍을 출력한다
    - $<(1, i), p>$,  $<(2, i), p>$, …  $<(i, i), p>$,  $<(i, i+1), p>$ …  $<(i, m), p>$가각
- Reduce Function
    - $(x, y)$ : $D_x$ 파티션과 $D_y$ 파티션 사이의 모든 레코드 쌍에 대하여 조인 $(x \le y)$
    - $x = y$ : $D_x$ ( = $D_y$) 파티션에 있는 모든 레코드 쌍의 조인
    - $x \ne y$ : $D_x$ 파티션과 $D_y$ 파티션 사이의 모든 레코드 쌍의 조인

# 빅데이터 분산 사전학습 (5)

## Common Item Counting for Every Pair of Sets

싱글 테이블에서 셀프 조인을 통하여 집합 형태의 데이터들의 공통 아이템들의 개수를 세기

---

### Common Item Counting for Every Pair of Sets

---

- Set 데이터에 있는 모든 튜플 간에 중복된 Item 개수를 카운트한다
- All Pair Partition 방법을 사용하면, 데이터의 모든 튜플 쌍을 살펴봐야하는데, Inverted Index를 사용하면 한개의 Item이라도 중복되는 튜플 쌍만 살펴보아 빠르게 할 수 있다.
- 과정
    1. 각 아이템들에 대한 Inverted Index를 만든다.
    
    | RID | Items |
    | --- | --- |
    | 1 | C D F |
    | 2 | A B E F G |
    | 3 | A B C D E |
    | 4 | B C D E F |
    | 5 | A E G |
    
    원본 테이블
    
    | Items | RID |
    | --- | --- |
    | A | 2 3 5 |
    | B | 2 3 4 |
    | C | 1 3 4 |
    | D | 1 3 4 |
    | E | 2 3 4 5 |
    | F | 1 2 4 |
    | G | 2 5 |
    
    Inverted Index
    
    1. Inverted Index를 스캔하면서 각 Item에 대한 리스트 마다 들어있는 record의 모든 ID 쌍에 대하여 Hash Table에 있는 Count를 증가시킨다.
        
        
        | Items | RID |
        | --- | --- |
        | A | 2 3 5 |
        | B | 2 3 4 |
        | C | 1 3 4 |
        | D | 1 3 4 |
        | E | 2 3 4 5 |
        | F | 1 2 4 |
        | G | 2 5 |
        
        Inverted Index
        
        | Candidate Pairs | Overlap |
        | --- | --- |
        | (2, 3) | 3 |
        | (3, 5) | 1 |
        | (2, 5) | 1 |
        | (3, 4) | 4 |
        | (2, 4) | 3 |
        | (1, 3) | 2 |
        | (1, 4) | 3 |
        | (4, 5) | 1 |
        | (2, 5) | 2 |
        | (3, 5) | 1 |
        | (1, 2) | 1 |
        
        Global hash table
        

## Top-K Closest Point Search

### Top-K Closest Point Search Algorithm

---

- 질의 포인트와 점들로 구성된 데이터 셋이 있을 때 질의 포인트로부터 가장 가까운 K개 포인트를 뽑는다.
- Max-Heap 자료구조를 사용한다.
- 각 레코드들은 n-dimensional Point로 구성 됨.

<aside>
💡 Max-Heap

---

![스크린샷 2022-09-02 오전 5.50.50.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e25d26fe-6693-45a6-9985-ab3066505731/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-09-02_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_5.50.50.png)

- Binary tree 형태인데, 어떤 노드에서든 부모가 자식보다 같거나 큰 수를 가진다.
- root node에 가장 큰 수가 담긴다.
</aside>

### Max-Heap을 사용한 가장 작은 Top-K개 숫자를 찾는 법

---

- 데이터를 읽어가면서 Map-Heap에는 현재까지 본 수 중에서 가장 작은 K개의 숫자를 유지한다.
- K = n일 때
    - n을 넘지 않았으면 수를 읽기 전까지 무조건 Max-Heap에 넣는다.
    - n을 넘어 갔을 때
        - Max-Heap의 root node와 비교 후 더 작으면 루트를 삭제하고 그 숫자를 넣는다.
        - 작지 않다면 그냥 넘어간다.

![스크린샷 2022-09-02 오전 6.01.33.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e7c7cf4e-8338-4d8b-bec5-ee9c675d4a96/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-09-02_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_6.01.33.png)

### Illustration of Top-K Closest point Search : Phase 1

---

- Map Function
    - 각 점을 입력 받는다.
    - 파티션을 m개로 나눌 때 점의 파티션 아이디를 pid라고 하면, (key, value) 쌍을 (pid, input)으로 한다.
- Reduce Function
    - Query point와 Value-list에 있는 포인트들과 거리 계산 후 가장 가까운 K개만 출력

### Illustration of Top-K Closest point Search : Phase 2

---

- Map Function
    - 각 점을 입력 받는다.
    - 똑같은 값 *를 key로 하고, value는 입력 포인트 그대로 하여 (key, value) 쌍을 내보낸다.
- Reduce Function
    - 각 reduce 함수마다 출력한 포인트들을 모아서 가장 가까운 k개만 출력한다.