# ****Android Developer Fundamentals Unit 1.1****

> [https://developer.android.com/courses/fundamentals-training/toc-v2](https://developer.android.com/courses/fundamentals-training/toc-v2)를 참고하여 작성하였습니다.
> 

## 안드로이드 프로젝트 디렉토리 구조

---

```jsx
APP
├── menifests
├── java
└── res
```

### menifests

- Androidmenifest.xml
    - Android 빌드 도구, Android 운영체제 및 Google Play에 앱에 관한 필수 정보를 설명함.
    - 애플리케이션의 각종 권한 역시 설정 가능

### java

- 자바 코드가 들어감
- 안드로이드의 생애주기 파악해놓기.

### res

- 각종 리소스 파일들
- xml등등

### Unit 1을 들으며 참고하면 좋을 내용들

---

1. **build.gradle(Module: hello_world.app)**에서 **minumum SDK level**을 설정할 수 있다.
    
    ```yaml
    defaultConfig {
            applicationId "com.wondae.mysms"
            minSdk 21
            targetSdk 32
            versionCode 1
            versionName "1.0"
    
            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    ```
    
    이후 **Sync Project with Gradle Files**를 통해 설정을 반영한다.
    
2. Logcat Pane을 통하여 로그 상태 확인
    
    **Logcat**은 기기에서 오류가 발생할 때의 스택 트레이스와 앱에서 **Log** 클래스로 작성한 메시지를 비롯하여 시스템 메시지의 로그를 덤프하는 명령줄 도구.
    
    ```java
    /*
    * Log : Logcat에 로그를 작성하기 위한 클래스
    * d : 디버그 로그 레벨 세팅 e(error), w(warn), i(info)가 있음
    * "MainActivity" : logcat에 필터 처리할 떄 사용할 태그
    * "Hello World" : 메세지
    */
    Log.d("MainActivity", "Hello World");
    ```
    
    - 컨벤션에 따르면 log tag는 Activity를 위한 상수로 정의된다.
    
    ```java
    // 로그 예시
    11-24 14:06:59.001 4696-4696/? D/MainActivity: Hello World
    ```
    
3. 개발한 앱을 실제 기기에서 돌리는 방법
    
    설정 > 핸드폰 정보 > 빌드번호를 7번 터치하여 개발자도구를 통하여 USB 디버깅 옵션을 켠다.
    

### Coding Challenge (optional)

---

1. Create a new project in Android Studio.
2. Change the "Hello World" greeting to "Happy Birthday to " and the name of someone with a recent birthday.
3. (Optional) Take a screenshot of your finished app and email it to someone whose birthday you forgot.
4. A common use of the `[Log](https://developer.android.com/reference/android/util/Log.html?hl=ko)` class is to log [Java exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/) when they occur in your program. There are some useful methods, such as [Log.e()](https://developer.android.com/reference/android/util/Log.html?hl=ko#e(java.lang.String,%20java.lang.String)), that you can use for this purpose. Explore methods you can use to include an exception with a `Log` message. Then, write code in your app to trigger and log an exception.

### 참고 링크

---

Android Studio documentation:

- [Android Studio download page](http://developer.android.com/sdk/index.html?hl=ko) : 안드로이드 스튜디오 다운로드
- [Android Studio release notes](https://developer.android.com/studio/releases/index.html?hl=ko)
- [Meet Android Studio](http://developer.android.com/tools/studio/index.html?hl=ko) : 레퍼런스 문서
- [Logcat command-line tool](http://developer.android.com/tools/debugging/debugging-log.html?hl=ko)
- [App Manifest Overview](http://developer.android.com/guide/topics/manifest/manifest-intro.html?hl=ko)
- [Configure your build](https://developer.android.com/studio/build/index.html?hl=ko)
- `[Log](https://developer.android.com/reference/android/util/Log.html?hl=ko)` class
- [Create and Manage Virtual Devices](https://developer.android.com/studio/run/managing-avds.html?hl=ko)

Other:

- [Gradle site](https://gradle.org/) : 빌드할 떄 사용하는 도구인 gradle 사이트
- [Apache Groovy syntax](http://groovy-lang.org/syntax.html) : 자바에 다른 언어의 특징을 추가한 다른 언어
- [Gradle Wikipedia page](https://en.wikipedia.org/wiki/Gradle)

----

### View 종류들

---

- TextView : 텍스트 표현
- EditText : 유저가 텍스트를 편집 가능하게 함
- Button : 클릭 가능한 요소로서 상호작용을 가능하게 함 (라디오 버튼, 체크박스, 스피너)
- ScrollView & RecyclerView : 스크롤 가능한 아이템을 보여줌
- ImageView : 사진들을 보여줌
- ContraintLayout & LinearLayout : 다른 뷰 요소를 담고, 포지셔닝 함.

### Activity

---

- 자바의 화면 출력이나 UI를 관리하는 것은 Activity 클래스를 상속 받아서 활용.
- 뷰의 레이아웃은 XML 파일을 통해 관리됨.

### 기타

---

- gravity : View가 View 그룹 혹은 부모 View에서 어떻게 정렬될 지 정함. 실습 때 정한 Center는 ConstraintLayout의 Center에 놓게 됨.
- makeText() : Toast 클래스를 만드는 팩토리 함수
- Toast.LENGTH_SHORT, Toast.LENGTH_LONG : 화면에 표시되는 시간 조절

---

### TextView, ScrollView

---

- TextView
    - 화면에 텍스트를 표시하는 View, 레이아웃은 xml 파일을 통해 관리됨.
- ScrollView
    - FrameLayout의 서브 클래스,
    - 오로지 하나의 뷰의 전체 내용을 스크롤로 출력. 그 뷰는 ViewGroup일 수 있음.
    - ScrollView 내부의 뷰는 보통 세로로 정렬된 LinearLayout을 추천함.
    - ScrollView 내부의 뷰는 보이지 않더라도 메모리에 저장됨.

### String.xml

---

- xml 속 데이터를 추가할 때 참고할 점
    - 줄바꿈 : \n
    - 볼드 : <b></b>
    - 이탤릭 : <i></i>
    - 링크 : TextView 내부에 `android:autoLink="web"`달기