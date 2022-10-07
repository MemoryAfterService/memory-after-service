# Android fundamentals 01.1: Android Studio and Hello World



## 1. Welcome

### What you should already know

✔ Understand the general software development process for object-oriented applications using an IDE (integrated development environment) such as Android Studio.

✔ Experience in object-oriented programming, with some of it focused on the Java programming language.



### What you'll need

✔ A computer running Chrome OS, Windows, Linux, or Mac running OS X.

✔ Internet access or an alternative way of loading the latest Android Studio and Java installations onto your computer.



### What you'll learn

✔ How to install and use the Android Studio IDE.

✔ How to use the development process for building Android apps.

✔ How to create an Android project from a template.

✔ How to add log messages to your app for debugging purposes.



### What you'll do

✔ Install the Android Studio development environment.

✔ Create an emulator (virtual device) to run your app on your computer.

✔ Create and run the Hello World app on the virtual and physical devices.

✔ Explore the project layout.

✔ Generate and view log messages from your app.

✔ Explore the `AndroidManifest.xml` file.



## 2. App overview

new project for the Hello World app.

* The simple app displays the string "Hello World" on the screen of the Android virtual or physical device.



## 3. Task 1: Install Android Studio

Android Studio provides a complete integrated development environment (IDE).

* an advanced code editor 
* a set of app templates

Tools

* for development
* for debugging
* for testing
* for performance that make it faster and easier to develop apps.

You can test your apps with a large range of preconfigured emulators or on your own mobile device, build production apps, and publish on the Google Play store.



## 4. Task 2: Create the Hello World app

### 1. Create the app project

To verify the Android studio, we will create an app that displays "Hello World" whether Android studio is correctly installed. And also to learn the basics of developing with Android Studio.

1. Open Android Studio if it is not already opened.
2. In the main **Welcome to Android Studio** window, click **New Project**.
3. An Activity is a single, focused thing that the user can do. It is a crucial component of any Android app. Android Studio provides Activity templates to help you get started. For the Hello World project, choose **Empty Activity** as shown below, and click **Next**.

![image-20220905173951240](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905173951240.png)

![image-20220905174349800](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905174349800.png)

✔ **Hello World**

✔ **com.example.helloworld**

✔ **Save location**

✔ **API 21: Android 5.0 (Lollipop)**

✔ **Use legacy android.support libraries**

✨ **Finish**



Android Studio creates a folder for your projects, and builds the project with Gradle (this may take a few minutes).

![image-20220905232422264](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232422264.png)

```java
package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```



### 2. Explore the Project > Android pane

![image-20220905232617105](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232617105.png)



### 3. Explore the Gradle Scripts folder

![image-20220905232638487](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232638487.png)



Follow these steps to explore the Gradle system:

1. If the **Gradle Scripts** folder is not expanded, click the arrow to expand it.

This folder contains all the files needed by the build system.

2. Look for the **settings.gradle (Project Settings)** file.

#### **settings.gradle (Project Settings)** 

```gradle
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Hello World"
include ':app'
```

This is where you'll find the project-level repository settings and the modules to include when building your app

The Gradle settings file is part of the standard project structure for an Android app. 

Most of the time, you won't need to make any changes to this file, but it's still useful to understand its contents.

✔ default



#### **settings.gradle (Project Settings)** 

In addition to the project-level `build.gradle` file, each module has a `build.gradle` file of its own, which allows you to configure build settings for each specific module (the HelloWorld app has only one module). 

Configuring these build settings allows you to provide custom packaging options, such as additional build types and product flavors. You can also override settings in the `AndroidManifest.xml` file or the top-level `build.gradle` file.



This file is most often the file to edit when changing app-level configurations, such as declaring dependencies in the `dependencies` section. You can declare a library dependency using one of several different dependency configurations. Each dependency configuration provides Gradle different instructions about how to use the library. 

For example, the statement `implementation fileTree(dir: 'libs', include: ['*.jar'])` adds a dependency of all ".jar" files inside the `libs` directory.

```gradle
plugins {
    id 'com.android.application'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.helloworld"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.5.0'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```



### 4. Explore the app and res folders

All code and resources for the app are located within the `app` and `res` folders.

**app>java>com.example.helloworld>MainActivity.java**

```java
package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

![image-20220906193800363](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906193800363.png)

The **com.example.helloworld** (or the domain name you have specified) folder contains all the files for an app package. 

The other two folders are used 

✔ for testing 

✔ for described 

in another lesson. 

For the Hello World app, there is only one package and it contains `MainActivity.java`. 

The name of the first `Activity` (screen) the user sees, which also initializes app-wide resources, is customarily called **MainActivity** (the file extension is omitted in the **Project > Android** pane).



### 5. Explore the mainfests folder

The `manifests` folder contains files that provide essential information about your app to the Android system, which the system must have before it can run any of the app's code.

**AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.helloworld">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.HelloWorld"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

The `AndroidManifest.xml` file describes all of the components of your Android app. All components for an app, such as each `Activity`, must be declared in this XML file. 

For an introduction, see [App Manifest Overview](https://developer.android.com/guide/topics/manifest/manifest-intro.html?hl=ko).



## 5. Task 3: Use a virtual device (emulator)

Using the Device Manager, you define the hardware characteristics of a device, , its API level, storage, skin and other properties and save it as a virtual device.

With virtual devices, you can test apps on different device configurations (such as tablets and phones) with different API levels, without having to use physical devices.

###  1. Create an Android virtual device (AVD)

![image-20220906230953784](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906230953784.png)

![image-20220906231020419](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906231020419.png)

![image-20220906231032681](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906231032681.png)

![image-20220906231100170](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906231100170.png)

![image-20220906230850332](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906230850332.png)

![image-20220906231606418](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906231606418.png)

**Tip**: When testing on a virtual device, it is a good practice to start it up once, at the very beginning of your session. 

You should not close it until you are done testing your app, so that your app doesn't have to go through the device startup process again. 

To close the virtual device, click the **X** button at the top of the emulator, choose **Quit** from the menu, or press **Control-Q** in Windows or **Command-Q** in macOS.



## ❕ 6. Task 4: (Optional) Use a physical device



## 7. Task 5: Change the app Gradle configuration

In this task you will change something about the app configuration in the `build.gradle (Module: Hello_World.app)` file in order to <u>learn how to make changes and synchronize them to your Android Studio project.</u>



### 1. Change the minimum SDK version for the app

![image-20220906232030922](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906232030922.png)

```gradle
    defaultConfig {
        applicationId "com.example.helloworld"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
```

**minSdk 21** into **minSdk 23**

The code editor shows a notification bar at the top with the **Sync Now** link.



### 2. Sync the new Gradle configuration

When you make changes to the build configuration files in a project, Android Studio requires that you sync the project files so that it can import the build configuration changes and run some checks to make sure the configuration won't create build errors.

![image-20220906232448054](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906232448054.png)

To sync the project files, click **Sync Now** in the notification bar that appears when making a change (as shown in the previous figure), or click the **Sync Project with Gradle Files** icon ![click Sync Now in the notification bar that appears when making a change (as shown in the previous figure), or click the Sync Project with Gradle Files icon [ICON HERE] in the toolbar. [IMAGEINFO]: ic_gradle_sync.png, Sync Project with Gradle Files](https://developer.android.com/static/codelabs/android-training-hello-world/img/434e346c96f39907.png?hl=ko) in the toolbar.

When the Gradle synchronization is finished, the message `Gradle build finished` appears in the bottom left corner of the Android Studio window.![image-20220906232658686](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906232658686.png)



## 8. Task 6: Add log statements to your app

In this task, you will add [`Log`](https://developer.android.com/reference/android/util/Log.html?hl=ko) statements to your app, which display messages in the **Logcat** pane. `Log` messages are a powerful debugging tool that you can use to check on values, execution paths, and report exceptions.



### 1. View the Logcat pane

![image-20220906233112001](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906233112001.png)

1. The **Logcat** tab for opening and closing the **Logcat** pane, which displays information about your app as it is running. If you add `Log` statements to your app, `Log` messages appear here.
2. The `Log` level menu is set to **Verbose** (the default), which shows all `Log` messages. Other settings include **Debug**, **Error**, **Info**, and **Warn**.



### 2. Add log statements to your app

`Log` statements in your app code display messages in the Logcat pane. 

**예시**

`Log.d("MainActivity", "Hello World"); `

- `Log`: The `Log` class for sending log messages to the Logcat pane.
- `d`: The **Debug** `Log` level setting to filter log message display in the Logcat pane. Other log levels are `e` for **Error**, `w` for **Warn**, and `i` for **Info**.
- `"MainActivity"`: The first argument is a tag which can be used to filter messages in the Logcat pane. This is commonly the name of the `Activity` from which the message originates. However, you can make this anything that is useful to you for debugging.

By convention, log tags are defined as constants for the `Activity`:

```JAVA
private static final String LOG_TAG = MainActivity.class.getSimpleName(); 
```

* `"Hello world"`: The second argument is the actual message.

![image-20220906233906195](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220906233906195.png)

Follow these steps:

1. Open your Hello World app in Android studio, and open `MainActivity`.
2. To add unambiguous imports automatically to your project (such as `android.util.Log` required for using `Log`), choose **File > Settings** in Windows, or **Android Studio > Preferences** in macOS.
3. Choose **Editor > General >Auto Import**. Select all checkboxes and set **Insert imports on paste** to **Always**.
4. Click **Apply** and then click **OK**.
5. In the `onCreate()` method of `MainActivity`, add the following statement:



### MainActivity.java

```java
package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Hello World");
    }
}
```

6. If the Logcat pane is not already open, click the **Logcat** tab at the bottom of Android Studio to open it.

7. Check that the name of the target and package name of the app are correct.

8. Change the `Log` level in the **Logcat** pane to **Debug** (or leave as **Verbose** since there are so few log messages).

9. Run your app.



The following message should appear in the Logcat pane:

`11-24 14:06:59.001 4696-4696/? D/MainActivity: Hello World`



## ❕ 9. Coding challenge

 **Challenge:** Now that you are set up and familiar with the basic development workflow, do the following:

1. Create a new project in Android Studio.
2. Change the "Hello World" greeting to "Happy Birthday to " and the name of someone with a recent birthday.
3. (Optional) Take a screenshot of your finished app and email it to someone whose birthday you forgot.
4. A common use of the `Log` class is to log **Java exceptions** when they occur in your program. There are some useful methods,that you can use for this purpose. Explore methods you can use to include an exception with a `Log` message. Then, write code in your app to trigger and log an exception.



## 10. Summary

✔ To install Android Studio, go to [Android Studio](https://developer.android.com/sdk/index.html?hl=ko) and follow the instructions to download and install it.

✔ When creating a new app, ensure that **API 21: Android 5.0 (Lollipop)** is set as the Minimum SDK.

✔ To see the app's Android hierarchy in the Project pane, click the **Project** tab in the vertical tab column, and then choose **Android** in the popup menu at the top.

✔ Edit the `build.gradle (Module: Hello_World.app)` file when you need to add new libraries to your project or change library versions.

✔ All code and resources for the app are located within the `app` and `res` folders. 

* The `java` folder includes activities, tests, and other components in Java source code. 
* The `res` folder holds resources, such as layouts, strings, and images.

✔ Edit the `AndroidManifest.xml` file to add features, components and permissions to your Android app. 

* All components for an app, such as multiple activities, must be declared in this XML file.

✔ Use the [Device Manager](http://developer.android.com/tools/devices/managing-avds.html?hl=ko) to create a virtual device (also known as an emulator) to run your app.

✔ Add [`Log`](https://developer.android.com/reference/android/util/Log.html?hl=ko) statements to your app, which display messages in the Logcat pane as a basic tool for debugging.

✔ To run your app on a physical Android device using Android Studio, turn on USB Debugging on the device. Open **Settings > About phone** and tap **Build number** seven times. Return to the previous screen (**Settings**), and tap **Developer options**. Choose **USB Debugging**.



## 11. ETC.

### Related concepts

The related concept documentation is in [1.0: Introduction to Android](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-0-c-introduction-to-android/1-0-c-introduction-to-android.html) and [1.1 Your first Android app](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-1-c-your-first-android-app/1-1-c-your-first-android-app.html).



### Learn more
Android Studio documentation:

- [Android Studio download page](http://developer.android.com/sdk/index.html?hl=ko)
- [Android Studio release notes](https://developer.android.com/studio/releases/index.html?hl=ko)
- [Meet Android Studio](http://developer.android.com/tools/studio/index.html?hl=ko)
- [Logcat command-line tool](http://developer.android.com/tools/debugging/debugging-log.html?hl=ko)
- [App Manifest Overview](http://developer.android.com/guide/topics/manifest/manifest-intro.html?hl=ko)
- [Configure your build](https://developer.android.com/studio/build/index.html?hl=ko)
- [`Log`](https://developer.android.com/reference/android/util/Log.html?hl=ko) class
- [Create and Manage Virtual Devices](https://developer.android.com/studio/run/managing-avds.html?hl=ko)

Other:

- [Gradle site](https://gradle.org/)
- [Apache Groovy syntax](http://groovy-lang.org/syntax.html)
- [Gradle Wikipedia page](https://en.wikipedia.org/wiki/Gradle)

