# Android fundamentals 01.2 Part A: Your first interactive UI



## 1. Welcome

### Introduction

The user interface (UI) that appears on a screen of an Android device consists of a hierarchy of objects called *views* — every element of the screen is a View. The `View` class represents the basic building block for all UI components, and the base class for classes that provide interactive UI components such as buttons, checkboxes, and text entry fields. Commonly used `View` subclasses described over several lessons include:

- `TextView` for displaying text.
- `EditText` to enable the user to enter and edit text.
- `Button`and other clickable elements (such as `RadioButton`, and `Spinner`) to provide interactive behavior.
- `ScrollView` and `RecyclerView`to display scrollable items.
- `ImageView` for displaying images.
- `ConstraintLayout` and `LinearLayout` for containing other `View` elements and positioning them.

The Java code that displays and drives the UI is contained in a class that extends `Activity`. An `Activity` is usually associated with a layout of UI views defined as an XML (eXtended Markup Language) file. This XML file is usually named after its `Activity` and defines the layout of `View` elements on the screen.

For example, the `MainActivity` code in the Hello World app displays a layout defined in the `activity_main.xml` layout file, which includes a `TextView` with the text "Hello World".

In more complex apps, an `Activity` might implement actions to respond to user taps, draw graphical content, or request data from a database or the internet. 

### What you should already know

You should be familiar with:

- How to install and open Android Studio.
- How to create the HelloWorld app.
- How to run the HelloWorld app.



### What you'll learn

- How to create an app with interactive behavior.
- How to use the layout editor to design a layout.
- How to edit the layout in XML.
- A lot of new terminology. 



### What you'll do

- Create an app and add two `Button` elements and a `TextView` to the layout.
- Manipulate each element in the `ConstraintLayout` to constrain them to the margins and other elements.
- Change UI element attributes.
- Edit the app's layout in XML.
- Extract hardcoded strings into string resources.
- Implement click-handler methods to display messages on the screen when the user taps each `Button`.



## 2. App overview

The HelloToast app consists of two `Button` elements and one `TextView`. 

When the user taps the first `Button`, it displays a short message (a [`Toast`) on the screen. 

Tapping the second `Button` increases a "click" counter displayed in the `TextView`, which starts at zero.



## 3. Task 1: Create and explore a new project

### 1. Create the Android Studio project

![image-20220907172513183](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907172513183.png)

2. Select **Run > Run app** or click the **Run icon** ![Run Icon](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/6c65750f2ce7f651.png?hl=ko) in the toolbar to build and execute the app on the emulator or your device.



### 2. Explore the layout editor

Android Studio provides the layout editor for quickly building an app's layout of user interface (UI) elements.

It lets you drag elements to a visual design and blueprint view, position them in the layout, add constraints, and set attributes. 

*Constraints* determine the position of a UI element within the layout. A constraint represents a connection or alignment to another view, the parent layout, or an invisible guideline.

![image-20220907173637177](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907173637177.png)

2️⃣ You use the **Design** tab to manipulate elements and the layout, and the **Code** tab to edit the XML code for the layout. The **Split** tab allows you to edit the code and view the layout at the same time.

3️⃣ The **Palette** pane shows UI elements that you can use in your app's layout.

4️⃣ The **Component Tree** pane shows the view hierarchy of UI elements. 

6️⃣ The **Attributes** tab displays the **Attributes** pane for setting properties for a UI element.



## 4. Task 2: Add View elements in the layout editor

In this task you create the UI layout for the HelloToast app in the layout editor using the `ConstraintLayout` features. You can create the constraints manually, as shown later, or automatically using the **Autoconnect** tool.



### 1. Examine the element constraints

1. Open `activity_main.xml` 

​	If there is no blueprint, click the **Select Design Surface** button ![click the Select Design Surface button [ICON HERE] in the toolbar and choose Design + Blueprint. [IMAGEINFO]: ic_blueprint_icon.png, Blueprint button](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/51cea7d6d81ab452.png?hl=ko) in the toolbar and choose **Design and Blueprint**.

2. The **Autoconnect** tool ![ensure that the tool is not disabled. [IMAGEINFO]: ic_autoconnect_enabled_icon.png, Autoconnect button](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/dd3846009e393c48.png?hl=ko) is also located in the toolbar. It is enabled by default. For this step, ensure that the tool is not disabled.

4. Select **TextView** in the **Component Tree** pane. The "Hello World" `TextView` is highlighted in the design and blueprint panes and the constraints for the element are visible.

5. Refer to the animated figure below for this step. Control+click the circular handle on the right side of the `TextView` to delete the horizontal constraint that binds the view to the right side of the layout. The `TextView` jumps to the left side because it is no longer constrained to the right side. To add back the horizontal constraint, click the same handle and drag a line to the right side of the layout.

![Deleting and adding a constraint](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/7ea557d8751d03e4.gif?hl=ko)

* Constraint handle!![image-20220907231546564](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907231546564.png)
* Resizing handle ![image-20220907231529780](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907231529780.png)



### 2. Add a Button to the layout

When enabled, the **Autoconnect** tool automatically creates two or more constraints for a UI element to the parent layout. After you drag the element to the layout, it creates constraints based on the element's position.

✔ Drag a **Button** from the **Palette** pane to any position in the layout.



### 3. Add a second Button to the layout

1. Drag another **Button** from the **Palette** pane to the middle of the layout as shown in the animated figure below.
2. Drag a vertical constraint to the bottom of the layout (refer to the figure below).

![Adding a second button with constraints](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/f06e6a4442437213.gif?hl=ko)

You can remove constraints from an element by selecting the element and hovering your pointer over it to show the Clear Constraints ![image-20220907232658736](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907232658736.png) button. 

To clear all constraints in the entire layout, click the **Clear All Constraints** tool in the toolbar. This tool is useful if you want to redo all the constraints in your layout.



## 5. Task 3: Change UI element attributes

The **Attributes**(known as properties) pane offers access to all of the XML attributes you can assign to a UI element. 



### 1. Change the Button size

![image-20220907233249425](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\Android fundamentals 01.2 Part A Your first interactive UI.assets\image-20220907233249425.png)

![Sizing panel in the Attributes pane](https://developer.android.com/static/codelabs/android-training-layout-editor-part-a/img/cb766d399da0945d.png?hl=ko)

✔ **1. Height control**

 This control specifies the `layout_height` attribute and appears in two segments on the top and **bottom** sides of the square. 

The angles indicate that this control is set to `wrap_content`

* `View` will expand vertically as needed to fit its contents. 
* The "0" indicates a standard margin set to 0dp.



✔ **2. Width control**

This control specifies the `layout_width` and appears in two segments on the **left** and **right** sides of the square.

The angles indicate that this control is set to `wrap_content`

* which means the `View` will expand horizontally as needed to fit its contents, up to a margin of 0dp.



**ConstraintLayout**

- The `match_constraint` setting expands the `View` element to fill its parent by width or height—up to a margin, if one is set. The parent in this case is the `ConstraintLayout`.
- The `wrap_content` setting shrinks the `View` element's dimensions so it is just big enough to enclose its content. If there is no content, the `View` element becomes <u>invisible.</u>

❗ To specify a fixed size that adjusts for the screen size of the device, use a fixed number of density-independent pixels (`dp` units). 

* For example, `16dp` means 16 density-independent pixels.



### 2. Change the Button attributes

The **Attributes** pane offers access to all of the attributes you can assign to a `View` element. 

You can enter values for each attribute, such as the `android:id`, `backgroundTint`, `textColor`, and `text` attributes.

✔ android:id ➡ button_toast

✔ backgroundTint ➡  @color/purple_200

✔ textColor ➡ @android:color/black

✔ text ➡ Toast



The `color/purple_200` is one of the predefined theme base colors defined in the `colors.xml` resource file. The original default purple is used for the app bar



## 6. Task 4: Add a TextEdit and set its attributes

One of the benefits of `ConstraintLayout` is the ability to align or otherwise constrain elements relative to other elements. In this task you will add a `TextView` in the middle of the layout, and constrain it horizontally to the margins and vertically to the two `Button` elements. You will then change the attributes for the `TextView` in the **Attributes** pane.



### 1. Add a TextView and constraints