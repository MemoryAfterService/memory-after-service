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

----
# Android Developer Fundametnal Unit 1.4

## 실습하면서 몰랐던 것들

---

### Launcher Icon 바꾸기

---

1. app/res를 우클릭 후 `New > Image Asset` 선택
2. foreground와 background를 취향에 따라 선택

- [https://material.io/design/iconography/product-icons.html](https://material.io/design/iconography/product-icons.html) 를 통해여 meterial UI에 맞는 아이콘 참고 가능

### Project template 사용

---

> Basic Activity Template 기준으로 설명
>| # | UI Description | Code reference |
| --- | --- | --- |
| 1 | 상태 바, 안드로이드 시스템이 제공하고 관리함. | 템플릿 코드에선 보이지 않음, 그러나 activity에서 접근 가능.
예를 들어 MainActivity.java에서 필요하다면 감출 수 있음. |
| 2 | 앱 바, 또는 액션 바로 불림.
시각적 구조와 표준화된 시각적 요소들, 그리고 안내를 제공함. | activity_main.xml에서 AppBarLayout의 Toolbarinside를 찾아보기.
하위 호환성을 위해서, AppBarLayout은 ActionBar와 기능적으로 같다.
앱 바의 모습을 바꾸기위해선 toolbar attributes를 바꿔야한다. |
| 3 | 앱 이름의 초기값은 패키지 이름에서 가져옴. (변경 가능) | AndroidManifest.xml에서 android:label=”@string/app_name” 참고.
string.xml에서 수정 가능 |
| 4 | 옵션-메뉴 오버플로우 버튼은 activity를 위한 메뉴를 가지고 있음.
오버플로우 버튼은 또한 설정과 검색 같은 전역 메뉴 옵션을 가지고 있음. | MainActivity.java에서 onOptionsItemSelected() 메서드는 유저가 메뉴 아이템을 선택했을 때 할 일을 구현함.
옵션-메뉴 아이템을 보려면, res/menu/menu_main.xml 참고. |
| 5 | CoordinateLayout ViewGroup은 UI 요소들이 상호작용하기 위한 메커니증을 제공하는 레이아웃.
앱 UI는 content_main.xml 파일에 있음. | activity_main.xml에서, includelayout 설명 참고.
content_main 레이아웃을 참고함. |
| 7 | Floating action button (FAB) | activity_main.xml에서, FloatingActionButton 요소 확인.
FAB은 clip-art 아이콘을 사용하는 UI .
MainActicity.java는 onCreate() 메서드로 FAB의 onclick() 리스너 설정. |

---
### Activity

---

- 유저가 하나의 집중된 태스크를 할 수 있게 하는 것.
- MainActivity는 앱이 시작될 때 보여지는 것.
- 다른 Activity가 시작되면, 이전의 것은 중지되고 스택에 보관됨.
- AndroidManifest.xml을 통해 다른 Activity 생성 가능

### Intent

---

- Activity는 Intent에 의해 시작되거나 활성화된다.
- Intent는 비동기적인 메세지로 activity간 혹은 다른 앱 구성요소에 요청을 보내는 데 사용된다.
- Intent는 명시적 혹은 암묵적일 수 있다.
    - 명시적 Intent는 목표를 알 수 있다.
    - 암묵적 Intent은 목표를 알 수 없다.
- Intent는 두가지 방법으로 데이터를 전송할 수 있다.
    - data field : URI를 통해 실행될 특정 데이터를 가리킴.
    - extras : URI가 아닌거나, 하나 이상의 정보를 보낼 떄 사용.
        - Bundle : key-value로 이루어짐.
---
### Android Lifecycle

---

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4b8820c6-7a49-4bb9-a5d0-b95529c05363/Untitled.png)

- onCreate()
    - 액티비티가 생길 때 실행
    - 반드시 구현해야 함.
    - 한번만 실행됨
        - 데이터 바인딩
        - ViewModel과 바인딩
- onStart()
    - 액티비티가 활성화 되면 실행
    - 이 함수 이후 액티비티가 사용자에게 보임
    - 여러번 실행 가능
- onResume()
    - 액티비티가 foreground 상태가 되면 실행
    - 포커스가 끝날 때까지 실행
    - 방해 이벤트가 발생하면 onPause() 실행
- onPause()
    - 액티비티가 background가 되면 실행
    - 일시중지 상태일 때 멈춰야 하는 것들 실행
- onStop()
    - 화면에 보이지 않을 때 실행
- onRestart()
    - stop상태에서 다시 실행될 때 실행
- onDestroy()
    - 액티비티가 끝났을 때 실행
    - 그 이전 모든 콜백을 실행해야 됨
        - onPause(), onStop()

### Activity Instance 상태 저장 후 불러오기

---

- Activity들은 생각보다 자주 새로고침 됨.
- 각 Activity들의 상태는 Bundle 객체에 key-value 상태로 저장됨.
- 시스템은 자동으로 Activity가 stop 상태로 가기 전에 bundle에 상태를 저장함.
- 데이터를 계속하여 보관하기 위해선 `onSaveInstanceState()` 메서드를 구현해야함.

<aside>
💡 onSaveInstanceState()

---

- onPause()와 onStop() 사이에 실행
- Intent가 활성화 되어 있는 동안은 Activity가 새로 만들어져도 데이터가 유지된다.
</aside>
