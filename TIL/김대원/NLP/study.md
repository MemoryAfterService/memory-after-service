## 자연어 처리 준비과정

---

### Framework, Library

---

1. Tensorflow : 구글에서 2015년에 공개한 머신러닝 라이브러리
2. Keras : 텐서플로우에 대한 추상화된 API 사용, TF보다 훨신 쉽게 작성 가능
3. Gensim : ML을 통한 토픽 모델링과 자연어 처리를 수행할 수 있게 하는 오픈 소스 라이브러리 
4. Scikit-learn : 파이썬 머신러닝 라이브러리, naive-bayse 분류, support vector 머신 등 사용 가능
5. Jupyter Notebook : 웹에서 소스를 실행할 수 있는 오픈소스 웹 어플리케이션

### NLTK, KoNLPy

---

- NLTK : 자연어 처리를 위한 파이썬 패키지. 제대로 사용하기 위해선 NLTK Data 설치 필요.
    
    ```python
    import NLTK
    
    nltk.download('리소스 이름')
    ```
    
- KoNLPy : 한국어 자연어 처리를 위한 형태소 분석기

### Pandas, Numpy, Matplotlib

---

- Pandas : 파이썬 데이터 처리를 위한 라이브러리
    - Series : 1차윈 배열의 key : value로 구성된 딕셔너리의 한 종류, key 대신 index 어휘 사용
    - DataFrame : 2차원 리스트, 행 방향의 index와 열 방향의 column으로 구성.
        - List, Series, Dictionary, np.ndarrays, dataframe으로부터 생성 가능
- Numpy : 수치 데이터를 다루는 파이썬 패키지, 벡터 및 행렬을 이용한 선형대수 계산에 자주 사용됨
    - np.ndarray : numpy의 핵심. ndim, shape로 크기 구분.
    - ndarray의 초기화 : np.zeros(), np.ones(), np.full(), np.eyes(), np.random.random() 등등
    - np.arrange() : 0 ~ n-1까지 값을 가지는 배열 생성
    - np.reshape() : 내부 데이터 변경 없이 배열의 구조를 바꿈,
    - numpy 슬라이싱 : [index, :]를 통해 특정 행이나 열의 원소 접근 가능
    - numpy는 정수로 인덱싱 가능
- Matplotlib : 데이터를 시각화하는 패키지

### Pandas Profiling

---

- EDA(Exploratory Data Analysis)를 위한 패키지

<aside>
💡 **spam.csv를 이용한 pandas profiling 실습**

---

1. google colab은 pandas_profiling의 버전이 낮아서 업데이트 필요
    
    `!pip install -U pandas_profiling` 으로 업데이트 해주기
    
2. [https://www.kaggle.com/uciml/sms-spam-collection-dataset](https://www.kaggle.com/uciml/sms-spam-collection-dataset) 을 다운받고 코랩에 추가
3. pd.read_csv로 파일을 읽은 다음 dataframe.profile_report() 사용

[Google Colaboratory](https://colab.research.google.com/drive/1E1NdfGg0Dm9op_EOPH8MnXwDHBymTwA_?usp=sharing)

</aside>

### Machine Learning Workflow

---

1. Acquisition (수집)
    - 머신러닝을 하기 위한 데이터를 수집하는 단계
    - nlp는 데이터셋을 말뭉치 혹은 corpus라 부름
        - corpus : 조사 및 연구 목적으로 특정 도메인으로부터 수집된 텍스트 집합
2. Inspection and Exploration (점검 및 탐색)
    - 데이터의 구조, 노이즈 데이터, 데이터 정제와 관련된 단계
    - EDA 단계라고도 부름
        - 변수들의 특징을 통하여 데이터의 특징과 구조적 관계 파악
    - 시각화 및 간단한 통계 테스트 진행 가능
3. Preprocessing and Cleaning (전처리 및 정제)
    - 전처리 과정을 통해서 토큰화, 정제, 정규화, 불용어 제거
    - 빠르고 정확하게 하기 위해선 사용하는 툴에 대한 다양한 라이브러리에 대한 이해 필요
    - 정말 복잡한 과정에선 전처리에서 ML 적용 가능
4. Modeling and Traing (모델링 및 훈련)
    - 머신 러닝 코드 작성
    - 전처리가 완로된 데이터를 ML 네트워크를 통해 학습
    - 모든 데이터를 사용하는 것이 아니라 일부를 빼서 validation과 test 용도로 남겨놔야 함.
5. Evaluation (평가)
    - test 데이터로 성능을 측정
6. Deployment (배포)
    - 만족할만한 성능이 나온다면 완성된 모델 배포
-----
# NLP 기본 : 텍스트 전처리 1. Tokenization

# 텍스트 전처리

---

- corpus 데이터를 사용하기 위해선 용도에 맞는 전처리 과정이 필요하다.

## Tokenization

---

### Word Tokenization

---

- 토큰의 기준을 단어로 하는 경우.
- word는 단어구, 의미를 갖는 문자열로 간주되기도 함.
- 보통 구두점이나 특문을 제거하는걸로 끝나지 않음 (제거함으로서 의미를 잃어버리는 경우가 있음.)
- 영어와 달리 한국어에서는 띄어쓰기 단위로 토큰 구분이 힘듬.
- 예시
    - 입력 : Time is an illusion, Lunchtime double so!
    - 출력 : [“Time”, “is”, “an”, “illusion”, “Lunchtime”, “double”, “so”]

### Selecting Criteria during Tokenization

---

- 토큰화의 기준을 정하는 것은 중요
- 각 tokenizer마다 다른 기준을 적용함. 따라서 적절한 것을 선택해야함
- 예시
    - Don’t, jones’ 와 같은 어퍼스트로피가 들어있는 경우

### Considering in Tokenization

---

- 구두점이나 특문을 단순 제외해선 안된다.
    - 약어나 고유명사등에 들어가는 것을 제외하면 의미를 잃어 버린다
    - Ph.D, AT&T, $45.55 etc.
- 줄임말과 단어 사이에 띄어쓰기가 있는 경우
    - 어퍼스트로피를 사용한 단어 및 기타
    - We’re, I’m, rock ‘n’ roll etc.
- 표준 토큰화 예제 : Penn Treebank Tokenization
    - Rule
        1. 하이픈으로 연결된 단어는 하나로 유지한다
        2. doesn’t와 같이 어퍼스트로피로 접힌 단어는 분리한다.

### Sentence Tokenization

---

- 코퍼스 내부에서 문장 단위로 구분하는 작업
- Sentence segmentation이라고 부르기도 함.
- 단순히 마침표나 느낌표, 물음표로 구분하기 보단, 코퍼스의 국적, 특문이 어떻게 사용되는지 파악하여 직접 규칙을 정의할 수 도 있음.
- 영어의 경우는 nltk.sent_tokenize(), 한국어는 kss 라이브러리를 사용한다.

### 한국어 토큰화의 어려움

---

- 영어의 경우는 합성어나 줄임말의 예외처리만 잘 한다면, 띄어쓰기 단위로 구분해도 잘 작동함.
1. 한글은 띄어쓰기 기준 토큰화(어절 토큰화)와 단어 토큰화가 다르기 때문에 어려움 (한국어는 교착어)
    - 한국어 토큰화의 경우 조사를 따로 분리해야 함
    - 따라서 형태소 단위로 분리해야함
    - 어절 토큰화와 형태소 토큰화 예시
        - 입력 : 에디가 책을 읽었다.
        - 어절 토큰화 결과 : [”에디가", “책을", “읽었다”]
        - 형태소 토큰화 결과 : [자립형태소 : [”에디”, “책”,], 의존형태소 : [”가, 을, 읽, 었, 다”]]

<aside>
💡 형태소 (morpheme)

---

- 뜻을 가진 가장 작은 단위
- 자립 형태소와 의존 형태소로 나뉨
    - 자립 형태소 : 접사, 어미, 조사와 상관없이 자립하여 사용할 수 있는 형태소. 형태소 자체가 단어가 된다. 체언, 수식언, 감탄사 등이 있다.
    - 의존 형태소 : 다른 형태소와 결합하여 사용되는 형태소. 접사, 어미, 조사, 어간 등이 있다.
</aside>

1. 한국어는 띄어쓰기가 영어보다 잘 지켜지지 않는다.
    - 뉴스기사와 같은 공적인 글은 맞춤법이 지켜지지만, 안 지켜지는 경우가 많다.
    - 특히 띄어쓰기가 안 지켜지는 경우가 많다
        - 띄어쓰기가 보편화된지 얼마 안됨
        - 없더라도 글이 이해됨 → 모아쓰기 방식의 특징

### Part-of-speech tagging (품사 태깅)

---

- 단어의 표기는 같지만, 품사에 따라서 뜻이 달라지는 경우가 있음 → fly(명사) : 파리, fly(동사) : 날다
- 이러한 해석을 보조하기 위해 토크나이즈 과정에서 품사를 태깅하기도 함.

<aside>
💡 **NLTK, KoNLPy를 이용한 영어 한국어 토큰화 실습**

</aside>
