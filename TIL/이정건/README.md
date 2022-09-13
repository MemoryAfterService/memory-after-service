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

### 0906

###### 자바공부

###### 

- 대소문자 구분, 길이 제한 없다
- 예약어(Reserved word) 사용 금지 - true(True는 가능) 등
- 숫자로 싲가해서는 안 된다
- 특수문자는 '_'와 '$'만을 허용
- 권장사항
  - 클래스 이름의 첫 글자는 항상 대문자(변수와 메서드 이름의 첫 글자는 항상 소문자)
  - 여러 단어 이름은 단어의 첫 글자를 대문자로 한다
  - 상수의 일므은 대문자로 한다, 단어는 '_'로 구분한다.

**변수, 상수, 리터럴**

- 변수(variable) - 하나의 값을 저장하기 위한 공간
- 상수(constant) 한 번만 값을 저장할 수 있는 공간
- 리터럴(literal) 그 자체로 값을 의미하는 것

**리터럴과 접미사**

- boolean power = true;
- char ch = 'A';
- char ch = '\u0041';
- char tab = '\t'
- byte b = 127;
- short s = 32767;
- int i = 100;
- int oct = 0100;
- int hex = 0x100;
- long l = 10000000000L;
- float f = 3.14f
- double d = 3.14d
- float f = 100f;
- 10. -> 10.0 
- .10  ->  0.10
- 10f -> 10.0f
- 3.14e3f -> 3140.0f
- 1e1 -> 10.0

**변수의 기본값과 초기화**

- 변수의 초기화 : 변수에 처음으로 값을 저장하는 것

  ​						지역변수는 사용되기 전에 반드시 초기화해준다

  | 자료형  | 기본값      |                                  |
  | ------- | ----------- | -------------------------------- |
  | boolean | false       | boolean isGood  = false;         |
  | char    | '\u0000'    | char grade = ' '; //공백         |
  | byte    | 0           | byte b = 0;                      |
  | short   | 0           | short s = 0;                     |
  | int     | 0           | int i = 0;                       |
  | long    | 0L          | long l = 0; //0L로 자동변환      |
  | float   | 0.0f        | float f = 0; //0.0f로 자동변환   |
  | double  | 0.0d or 0.0 | double d = 0; //0.0으로 자동변환 |
  | 참조형  | null        | String s1 = null;                |
  |         |             | String s2 = ""; // 빈 문자열     |

**문자와 문자열**

- char ch = 'AB' -> 에러
- char ch = ''; -> 에러
- String s1 = "A" + "B"; //"AB"
- "" + 7 -> "" + "7" -> "7"
- "" + 7 + 7 -> "7" + 7 -> "7" + "7" -> "77"
- 7 + 7 + "" -> 14 + "" -> "14"
- 문자열 + any type -> 문자열
- any type + 문자열 -> 문자열

**정수의 오버플로우(Overflow)**

- byte b = 127;

- byte b = 128; // 에러(-128 ~ 127)

- 000 ~ 999

  부호가 없는 정수 0000 ~ 1111

  부호가 있는 정수 1000 ~ 0111

형변환(Casting)

- 값의 타입을 다른 타입으로 변환하는 것

- boolean을 제외한 7개의 기본형은 서로 형변환이 가능

- float f = 1.6f;

- int i = (int)f;

  | 변환         | 수식      | 결과  |
  | ------------ | --------- | ----- |
  | int -> char  | (char)65  | 'A'   |
  | char -> int  | (int)'A'  | 65    |
  | float -> int | (int)1.6f | 1     |
  | int -> float | (float)10 | 10.0f |

- int -> byte (byte크기 감소, 값손실있음)

  int i2 = 300;

  byte b2 = (byte)i2; // 생략불가

- byte -> int(byte크기 증가, 값손실없음)

  byte b = 10;

  int i (int) b; //생략가능

**형식화된 출력 - printf()**

- println()의 단점 - 출력형식 지정불가

  - 실수의 자리수 조절불가

    System.out.println(10.0/3); //3.3333333...

  - 10진수로만 출력

    System.out.pringln(0x1A); // 26

- printf()로 출력형식 지정가능

  System.out.printf("%.2f", 10.3/3); // 3.33

  System.put.printf("%d", 0x1A); //26

  System.out.printf("%X", 0x1A); //1A

**printf()의 지시자**

| 지시자 | 설명           |
| ------ | -------------- |
| %d     | decimal        |
| %o     | octal          |
| %x, %X | hexa-decimal   |
| %f     | floating-point |
| %e, %E | exponent       |
| %c     | character      |
| %s     | string         |
| %b     | boolean        |

System.out.printf("age:%d year:%d\n", 14, 2017);

```
"age:14 year:2017\n"
					// 줄바꿈 o
```

System.out.printf("age:%d%n year:%d\n", 14, 2017);

```
"age:14"
 "year:2017"

```

- 10진수, 8진수, 16진수 출력

  System.out.printf("%d", 15); // 15

  System.out.printf("%o", 15); // 17

  System.out.printf("%x", 15); // f

  System.out.printf("%s", Integer.toBinaryString(15)); // 1111 2진수

- 8진수와 16진수에 접두사 붙이기

  System.out.printf("%#o, 15"); // 017
  System.out.printf("%#x, 15"); //0xf
  System.out.printf("%#X, 15"); //0XF

- 실수 출력을 위한 지시자 %f - 지수형식(%e), 간략한 형식(%g)

  float f = 123.456789f

  System.out.printf("%f", f); // 123.456787, 소수점 아래 6자리
  System.out.printf("%e", f) // 1.234568e+02, 지수형식System.out.printf("g", 123,456789); // 123.457, 간략한 형식
  System.out.printf("g", 0.00000001); // 1.00000e-8, 간략한 형식
  System.out.printf("[%5d]%n", 10); // [   10]
  System.out.printf("[%-5d]%n", 10); // [10   ]
  System.out.printf("[%05d]%n", 10); // [00010]

- %전체자리.소수점아래자리f

  System.out.printf("d = %14.10f%n", d); // 전체 14자리 중 소수점 아래 10자리만 출력

  소수점 왼쪽은 공백처리 소수점 오른쪽은 0으로 채운다

  System.out.printf("[%s]%n", url); // [www.codechobo.com/]
  System.out.printf("[%20s]%n", url); // [  www.codechobo.com/]
  System.out.printf("[%-20s]%n", url); // [www.codechobo.com/  ]
  System.out.printf("[%.8s]%n", url); // [www.code]

**Scanner**

- 화면으로부터 데이터를 입력받는 기능을 제공하는 클래스

- 조건

  - import문 추가

    import java.util.*;

  - Scanner객체의 생성

    Scanner scanner = new Scanner(System.in);

  - Scanner객체를 사용

    int num = scanner.nextInt(); // 화면에서 입력받은 정수를 num에 저장
    String imput = scanner.nextLine(); // 화면에서 입력받은 내용을 input에 저장
    int num = Integer.parseInt(input); // 문자열(input)을 숫자(num)로 변환

**연산자(Operator)**

어떠한 기능을 수행하는 기호(+,-,*,/ 등)

**피연산자(Operand)**

연산자의 작업 대상(변수, 상수, 리터럴, 수식)

**종류**

- 단항 연산자 : + - (타입) ++ -- ~ !

  - 증감 연산자 ++, --
    - 전위형 j = ++i; 
      - 값이 참조되기 전에 증가시킨다
    - 후위형 j = i++;
      - 값이 참조된 후에 증가시킨다.
  - 부호연산자 +, -
    - 피연산자에 1(+) 또는 -1(-)을 곱한다
  - 논리부정연산자 !
    - true는 false로, false는 true로 바꾼다
    - 피연산자가 boolean일 때만 사용가능
  - 비트전환연산자 - ~
    - 정수를 2진수로 표현했을 때, 1을 0으로 0은 1로 바꾼다.
    - 정수형에만 사용가능

- 이항 연산자

  특징(피연산자의 타입을 일치시키기 위해)
  	int보다 크기가 작은 타입은 int로 변환
  	피연산자 중 표현범위가 큰 타입으로 형변환 한다

  ```
  int a = 1,000,000;
  int b = 2,000,000;
  long c = a * b // c = -1454759936
  long c = (long)a*b; // c = 2,000,000,000,000
  
  long a = 1,000,000 * 1,000,000; // a = -727,379,968
  lomng b = 1,000,000 * 1,000,000L;
  // b = 1,000,000,000,000
  int c = 1,000,000 * 1,000,000 / 1,000,000;// c = -272
  in d = 1,000,000 / 1,000,000 * 1,000,000; // d = 1,000,000
  
  char c1 = 'a';
  char c2 = c1 + 1; // error
  char c2 = (char(c1 + 1); // b
  char c2 = ++c1; // b
  char c2 = c1++; // a
  int i = 'B' - 'A'; // 1
  int i = '2' - '0'; // 2
  
  float pi = 3.141592f;
  float shortPi = (int)(pi * 1000) / 1000f; //3.141f
  
  Math.round() : 소수점 첫째자리에서 반올림한 값을 반환
  float shortPi = Math.round(pi*1000) / 1000f; //3.142f
  ```

  

  - 산술 : + - * / % << >> >>>

    - 나머지 연산자 - %

      - 10 % 8 -> 2
      - 10 % -8 -> 2
      - -10 % 8 -> -2
      - -10 % -8 -> -2

    - 쉬프트연산자 << >> >>>

      -  x << n -> x*2^n

      - x >> n -> x/ 2^n

        

  - 비교 : > < >= <= == !=

    - 결과 값은 true or false

    - 기본형(boolean제외)과 참조형에 사용할 수 있으며, 참조형은 ==와 !=만 사용 가능

      ```
      '0' == 0 // 48 == 0, false
      'A' == 65 -> 65 != 65, false
      10.0d == 10.0f // 10.0d == 10.0d, true
      0.1d == 0.1f // 0.1d == 0.1d, false
      (double d = (double)0.1f;
      System.out.println(d); // 0.10000000149011612
      (float)0.1d == 0.1f // 0.1f == 0.1f, true
      ```

    - 

  - 논리 : && || & ^ |

    - 피연산자를 비트단위로 연산
    - float, double을 제외한 모든 기본형에 사용가능
    - OR(|)
    - AND(&)
    - XOR(^) -> 핑녀산자가 서로 다를 때 1
    - OR(||)
    - AND(&&)

  - 삼항 : ? :

    - (조건식) ? 식1 : 식2

  - 대입 : = op=

    - 오른쪽 피연산자의 값을 왼쪽 피연산자에 저장
    - 왼쪽 피연산자는 상수가 아니어야 한다

![image-20220828185937324](README.assets/image-20220828185937324.png)

**조건문과 반복문**

- 조건문은 조건식과 실행될 하나의 문장 또는 블럭{}으로 구성
- Java에서 조건문은 if문과 switch문 두가지
- if문 주로 사용, 경우의 수가 많은 경우 switch문 사용 고려
- 모든 switch문은 if문으로 변경 가능, 반대는 불가능 한 경우 많다

**if문**

조건식의 결과는 true or false

- if(조건식() {

  }

- if(조건식) {

  } else {

  }

- if(조건식1) {

  } else if(조건식2) {

  } else if(조건식3) {

  } else {

  }

**중첩 if문**

- if 문 안에 또 다른 if문을 중첩해서 넣을 수 있다
- if문의 중첩횟수에는 거의 제한이 없다

```
if (조건식1) {
	//
	if(조건식) {
		//
	}else {
		//	
	}
} else {
	//
}
```

**switch문**

- 조건식의 계산결과가 int타입의 정수와 문자열만 가능
- 조건식의 계산결과와 일치하는 case문으로 이동 후 break문을 만날 때까지 문장들 수행(break문이 없으면 switch문의 끝까지 진행)
- 일치하는 case문의 값이 없는 경우 default문으로 이동(default문 생략가능)
- case문의 값으로 변수를 사용할 수 없다.(리터럴, 상수, 문자열 상수만 가능)

```
switch (조건식) {
	case 값1 :
		//
		//
		break;
    case 값2 :
    	//
    	//
    	break;
    //
    default :
    	//
    	break
}
```

**중첩 switch문**

- switch문 안에 또 다른 switch문을 중첩해서 넣을 수 있다
- 중첩에 거의 제한이 없다

**Math.random()**

- Math클래스에 정의된 난수 발생함수
- 0.0과 1.0 사이의 double값을 반환
  (0.0 <= Math.random() < 1.0)

**반복문(for, while, do-while)**

- 문장 또는 문장들을 반복해서 수행할 때 사용

- 조건식과 수행할 블럭{} 또는 문장으로 구성

- 반복회수가 중요한 경우에 for문을 그 외에는 while문을 사용

- for문과 while문은 서로 변경가능

- do-while문은 while문의 변형으로 블럭{}이 최소한 한번은 수행될 것을 보장한다

  ```
  int i=0;
  do {
  	i++;
  	System.out.printIn(i);
  } while(i<=5);
  ```

  ```
  for(int i=1;i<=5;i++) {
  	System.out.printIn(i);
  }
  ```

  ```
  int i=1;
  while(i<=5) {
  	System.out.println(i);
  	i++;
  }
  ```

  **for문**

- 초기화, 조건식, 증감식, 그리고 수행할 블럭{} 또는 문장으로 구성

  ```
  for (초기화;조건식;증강식) {
  
  }
  //반복하려는 문장이 단 하나일 대는 중괄호{}생략 가능
  ```

  ```
  1. 초기화 -> 2. 조건식 -> 3. 수행될 문장 -> 4. 증강식(4->2 반복)
  ```

**중첩 for문**

- for문 안에 또 다른 for문을 포함시킬 수 있다

- for문의 중첩횟수에는 거의 제한이 없다

  ```
  for (int i=2; i<=9; i++) {
  	for(int j=1; j<=9; j++) {
  		System.out.printIn(i+" * " +j+ " = " = "i+j");
  	}
  }
  ```

  ```
  for (int i=2; i<=9; i++)
  	for(int j=1; j<=9; j++)
  		System.out.printIn(i+" * " +j+ " = " = "i+j");
  ```

**while문**

- 조건식과 수행할 블럭{} 또는 문장으로 구성

  ```
  while (조건식) {
  
  }
  ```

**중첩 while문**

- while문 안에 또 다른 while문 포함 가능
- 중첩횟수 거의 제한 없음

**do-while문**

- while문의 변형, 블럭{}을 먼저 수행한 다음에 조건식을 계산

- 블럭{}이 최소한 1번 이상 수행된다

  ```
  do {
  	//
  } while (조건식);
  ```

**break문**

- 자신이 포함된 하나읩 ㅏㄴ복문 또는 switch문을 빠져 나온다
- 주로 if문과 함께 사용해서 특정 조건을 만족하면 반복문을 벗어나게 한다

**continue문**

- 자신이 포함된 반복문의 끝으로 이동(다음 반복으로 넘어간다)
- continue문 이후의 문장들은 수행되지 않는다

**이름 붙은 반복문과 break, continue**

- 반복문 앞에 이름을 붙이고 그 이름을 break, continue와 같이 사용함으로써 둘 이상의 반복문을 벗어나거나 반복을 건너뛰는 것이 가능하다

  ```
  public static void main(String[] args)
  {
  	Loop1 : for(int i=2; i<=9; i++) {
  	for(int j=1; j <=9; j++) {
  	if (j==5);
  		break Loop1;
  		}
  	}
  }
  ```

**배열(array)**

- 여러 타입의 여러 변수를 하나의 묶음으로 다루는 것

- 많은 양의 값(데이터)을 다룰 때 유용

- 배열의 각 요소는 서로 연속적

  ```
  int score1=0, score2=0, score3=0, score4=0, score5=0
  ```

  ```
  int[] score = new int[5];
  ```

**선언과 생성**

배열의 선언(배열을 다루는데 필요한 변수 생성)

- 타입[] 변수이름;
  - int[] score;
  - String[] name;
- 타입 변수이름[];
  - int score[];
  - String name[];

배열의 생성(값을 저장할 공간이 생성)

```
int[] score; //선언
score = new int[5] //생성
->
int[] score = new int[5];
```

**배열의 초기화**

생성된 배열에 처음으로 값을 저장

```
int[] score = { 100, 90, 80, 70, 60}; // 1번
int[] score = new int[] { 100, 90, 80, 70, 60}; //2번
```

```
int[] score;
score = { 100, 90, 80, 70, 60} // 에러발생

int[] score;
score = new int[]{ 100, 90, 80, 70, 60}; // OK
```

```
int add{int [] arr}{

}

int result = add({ 100, 90, 80, 70, 60}}; // 에러 발생
int result = add(new int[]{ 100, 90, 80, 70 60}); // OK
```

**배열의 활용**

```
score[3] = 100; // 배열 score의 4번째 요소에 100을 저장
int value = score[3]; // 배열 score의 4번째 요소에 저장된 값을 읽어서 value에 저장
```

- 배열이름.length -> 배열의 길이를 알려준다

**다차원 배열의 선언과 생성**

'[]'의 개수가 차원의 수를 의미한다

```
타입[][] 변수이름; int[][] score;
타입 변수이름[][]; int score[][];
타입[] 변수이름[]; int[] score[];

int[][] score = new int[5][3]; // 5행 3열의 2차원 배열 생성
```

**가변배열**

다차원 배열에서 마지막 차수의 크기를 지정하지 않고 각각 다르게 지정

```
int[][] score = new int[5][];
score[0] = new int[4];
score[1] = new int[3];
score[2] = new int[2];
score[3] = new int[2];
score[4] = new int[3];
```

**배열의 복사**

- for문을 이용한 배열의 복사

  ```
  int[] number = {1,2,3,4,5};
  int[] newNumber = new int[10];
  
  for(int i=0; i<number.length;i++) {
  	newNumber[i] = number[i];
  }
  ```

- System.arraycopy()를 이용한 배열의 복사

  ```
  System.arraycopy(arr1, 0, arr2, 0, arr1.length);
  //arr1[0]에서 arr2[0]으로 arr.length개의 데이터를 복사
  ```

**사용자 입력받기 - 커맨드라인**

- 커맨드라인에서 입력된 값들은 문자열 배열에 담겨 main메서드에 전달

  ```
  class ArrayEx13
  {
  	public static void main(String[] args)
  	{
  		System.out.println("매개변수의 개수:
  "+args.length);
  		for(int i=0;i< args.length;i++) {
  			System.out.println("args[" +i + "] = \""+ args[i] + "\"");
  		}
  	}
  }
  ```

- Swing패키지의 JOptionPane.showInputDialog()를 사용

  ```
  import javax.swing.*;
  
  class ArrayEx16 {
  	Public static void main(String[] args)
  	{
  		int answer = (int).{Math.random() * 100} + 1;
  		int input = 0;
  		String temp = "";
  		int count = 0;
  		
  		do {
  			count++;
  			temp = JOptionPane.showInputDialog("1~100사이의 숫자를 입력하세요.");
  			
  			if(temp==null || temp.equals("-1")) break;
  			
  			System.out.println("입력값 : "+temp);
  			
  			input = Integer.parseInt(temp);
  		}
  	}
  }
  ```


### 0908

###### Tacademy hadoop 2강

하둡(3.3.0) 설치

- JAVA, JDK 1.8이상 버젼(java -version)
- IntelliJ, Maven Project를 들고 프로젝트를 만든다

Maven?

- dependencies들의 import, build를 용이하게 해주는 도구
- pom.xml 파일을 통해 세팅하여 동작하게 된다(dependency 정의, hadoop mvn repository 검색을 통해 설정한다)

vim 변경 적용 -> source

```
vi .bash_profile
source .bash.profile
```

hadoop-env..sh 수정 - 하둡 환경파일 설정

```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home

export HADOOP_HOME=/Users/SSAFY/Platform/hadoop-3.3.0

export HADOOP_CONF_DIR=${HADOOP_HOME}}/etc/hadoop
```

core-site.xml 수정 - 하둡 코어 설정

````
<configuration>
	<property>
		<name>fs.defaultFS</name>
		<value>hdfs://localhost:9000</value>
    </property>
```
    <property>
    	<name>dfs.namenode.name.dir</name>
    	<value>/Users/SSAFY/Platform/dfs/name
    </property>
```
</configuration>
````

hdfs-site.xml 수정 - 하둡 분산파일 시스템 설정값 설정

- 하둡은 원본파일 포함 3개의 복제파일을 가지게된다

```
<configuration>
	<property>
		<name>dfs.replication</name>
		<value>1</value> #replication을 하나만 해준다(데몬을 하나만 띄울 것이기 때문에)
    </property>
</configuration>
```

yarn-site.xml 수정 - 하둡 yarn에 대한 설정(map-reduce 설정)

ssh 로그인 가능 여부 테스트

```
ssh localhost #자신한테 로그인
안될경우 homedirectory로 간뒤에
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys
를 통해 key 생성 및 적용해준다
key를 지우는 방법
vi .ssh/authorized_keys를 통해서 들어간 후에 전부다 지워준다(dd를 연타하여 지운다)
```

하둡 네임노드 포맷

- 포맷 이후 데몬을 띄워야 실행이 되므로 선행 필요
- Platform/hadoop-3.3.0의 bin으로 가서 아래 명령어를 통해서 실행한다

```
bin/hdfs namenod -format
```

DFS 데몬 실행

```
sbin/start-dfs.sh
이후 localhost:9870/을 통해서 접속한다(namenode, 하둡분산파일시스템을 확인할 수 있는 관리도구)
localhost://9000 active가 뜬다
```

mapred-site.xml 수정

- map-reduce demon을 띄우기 위한 설정

```
<configuration>
	<property>
		<name>mapreduce.framework.name</name>
		<value>yarn></value>
	</property>
	<property>
		<name>mapreduce.application.classpath</name>
		<value>$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/*:$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/lib/*</value>
	</property>
</configuration>
```

```
sbin/start-yarn.sh를 통해 실행
```

```
이후 http:localhost:8088을 통해 접속 -> 어플리케이션 실행 상태를 볼 수 있는 관리도구
```

유의사항

```
/tmp/SSAFY/dfs/name/current에 대한 권한이 없을 경우, 이 경로를 수정해준다
그 방법은 
core-site.xml에서 ```로 주석처리한 부분을 적어주는것이다
```

### 0913

###### Tacademy hadoop 3강

분산환경 : 물리적으로 여러개의 서버가 하나의 클러스터처럼 동작하는 플랫폼

- Master-Slave 구조
  - Master Demon가 Slave Demon을 관리
  - Slave server들은 N대의 서버로 확장(scale-out)할 수 있다
  - Master의 안정성을 중요시한다
    -  부하가 가해지면 안된다(client와 slave가 직접 연결된다, 트래픽, 데이터를 주고받는다)
- Master가 없는 구조
  - Master가 가지고 있어야하는 정보들을 모든 노드들이 공유한다

구글 플랫폼

- 철학
  - 요약 : 분산 & 자동화
  - 한대의 고가 장비보다 여러 대의 저가 장비가 낫다
  - 데이터는 분산 저장한다(distributed computing) <-> 데이터를 공유스토리지에 공유하고 CPU Cores, 메모리를 늘려가면서 처리하는것(Parallel computing)
  - 시스템(H/W)은 언제든 죽을 수 있다(Smart S/W)
  - 시스템 확장이 쉬워야 한다

하둡

![image-20220912173247381](README.assets/image-20220912173247381.png)

- 특성
  - 추천대 이상의 리눅스 기반 범용 서버들을 하나의 클러스터로 사용
  - 마스터-슬레이브 구조
  - 파일은 블록(block)단위로 저장
  - 블록 데이터의 복제본 유지로 인한 신뢰성 보장(원본 포함 기본 3개의 복제본)
  - 높은 내고장성(Fault-Tolerance)
  - 데이터 처리의 지역성 보장

- 네트워크 및 데몬 구성
  - 단위는 Rack
    - DFS를 관리하는 Name Node
      - 데이터의 위치, 형식 보관
    - Job을 관리하는 Job Trakcer
    - 데이터를 저장, 관리하는 Data Node
      - 실 데이터 저장
    - 어플리케이션 업무를 수행하는 Task Trakcer
    - switch + master(name, job, secondary, ....) + slave(DN(Data Node)+TT(Task Tracker))
- 블록
  - 하둡에서 데이터를 저장하는 단위
  - 하나의 파일을 여러 개의 Block으로 저장
  - 설정에 의해 하나의 Block은 64MB 혹은 128MB 등의 큰 크기(탐색 비용을 최소화 하기 위해서 큰 크기로 나눈다)로 나누어 저장
  - 블록 크기가 128MB보다 작은 경우는 실제 크기 만큼만 용량을 차지한다
- 블록의 지역성(Locality)
  - 거치는 switch 최소화를 통해 실제 job을 가지는 node가 먼저 일을 수행할 수 있게 한다
  - 장점
    - 네트워크를 이용한 데이터 전송 시간 감소
    - 대용량 데이터 확인을 위한 디스크 탐색 시간 감소
    - 적절한 단위의 블록 크기를 이용한 CPU 처리시간 증가
  - 참고 
    - 클라우드 스토리지를 이용(ex) S3)할 경우 HDFS를 사용하는 것보다 성능 저하가 있을 수 있다
- 블록 캐싱
  - 데이터 노드에 저장된 데이터 중 자주 읽는 블록을 블록캐시라는 데이터 노드의 메모리에 명시적으로 캐싱하는것
  - 파일 단위로 캐싱할 수도 있어서 조인에 사용되는 데이터들을 등록하여 읽기 성능을 높일 수 있다

- 네임노드 역할
  - 전체 hdfs에 대한 Name Space 관리
  - DataNode로 부터 Block 리포트를 받는다
  - Data에 대한 Replication 유지를 위한 커맨더 역할 수행
  - 파일시스템 이미지 파일 관리(fsimage)
  - 파일시스템에 대한 Edit Log 관리

![image-20220912180924996](README.assets/image-20220912180924996.png)

- 보조 네임노드(SNN, Secondary Name Node)
  - 네임노드(NN)와 보조 네임노드(SNN)
    - Active/Standby 구조가 아니다
    - fsiimage와 edits 파일을 주기적으로 병합
  - 체크 포인트
    - 1시간 주기로 실행
    - edits 로그가 일정 사이즈 이상이면 실행
  - 이슈사항
    - 네임노드가 SPOF
    - 보조 네임노드의 장애 상황 감지 툴 없음

- 데이터노드(Datanode)
- ![image-20220912181515464](README.assets/image-20220912181515464.png)
  - 물리적으로 로컬 파일시스템에 HDFS데이터를 저장한다
  - DataNode는 HDFS에 대한 지식이 없다
  - 일반적으로 레이드 구성을 하지 않는다(JBOD(Just Bunch Of Disk구성), 사용하는 데이터 낭비 방지
  - 블록리포트: NameNode가 시작될 때, 그리고 (주기적으로)로컬 파일시스템에 있는 모든 HDFS 블록들을 검사 후 정상적인 블록의 목록을 만들NameNode에 전송

![image-20220912181636043](README.assets/image-20220912181636043.png)

