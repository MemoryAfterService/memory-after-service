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

![image-20220905173951240](C:\Users\multicampus\Desktop\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905173951240.png)

![image-20220905174349800](C:\Users\multicampus\Desktop\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905174349800.png)

✔ **Hello World**

✔ **com.example.helloworld**

✔ **Save location**

✔ **API 21: Android 5.0 (Lollipop)**

✔ **Use legacy android.support libraries**

✨ **Finish**



Android Studio creates a folder for your projects, and builds the project with Gradle (this may take a few minutes).

![image-20220905232422264](C:\Users\multicampus\Desktop\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232422264.png)



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

![image-20220905232617105](C:\Users\multicampus\Desktop\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232617105.png)



### 3. Explore the Gradle Scripts folder

![image-20220905232638487](C:\Users\multicampus\Desktop\Android fundamentals 01.1 Android Studio and Hello World.assets\image-20220905232638487.png)



Follow these steps to explore the Gradle system:

1. If the **Gradle Scripts** folder is not expanded, click the arrow to expand it.

This folder contains all the files needed by the build system.

2. Look for the **settings.gradle (Project Settings)** file.

#### settings.gradle

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

✔



### 4. Explore the app and res folders

All code and resources for the app are located within the `app` and `res` folders.
