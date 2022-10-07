**Select Project** 

Empty Activity



**res/values/colors.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="gray">#808080</color>
    <color name="mint">#2A9EAD</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```



**res/values/theme**

✔ 유지 But Tutorial은 night theme삭제

✔ if changed themes.xml 코드 수정

**acitvity_main.xml**

original

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```



✔ Change to LinearLayout

```xml
<LinearLayout></LinearLayout>
```

✔ Set orientation to vertical

```xml
android:orientation="vertical"
```

✔ Create LinearLayout inside the LinearLayout

```xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginBottom="20dp"
        android:layout_marginTop="20dp">
    </LinearLayout>
```

✔ Create 2 Buttons and 2 Textview

```xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginBottom="20dp"
        android:layout_marginTop="20dp">
        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="<-"
            android:textColor="@color/mint"
            android:background="null"
            android:textStyle="bold"
            android:onClick="previousMonthAction"
            android:textSize="20sp"/>

    <TextView
        android:id="@+id/monthYearTV"
        android:layout_width="0dp"
        android:layout_weight="2"
        android:layout_height="wrap_content"
        android:text="Sep 2022"
        android:textSize="20sp"
        android:textAlignment="center"
        android:textColor="@color/black"/>

        <Button
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="->"
            android:textColor="@color/mint"
            android:background="null"
            android:textStyle="bold"
            android:onClick="nextMonthAction"
            android:textSize="20sp"/>
```



✔ New LinearLayout and and textview from SUN to SAT

````xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="SUN"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="MON"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="TUE"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="WED"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="THU"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="FRI"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="SAT"
            android:textColor="@color/black"
            android:textAlignment="center"
            android:textSize="16sp"></TextView>
    </LinearLayout>
````

![image-20220918161555697](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918161555697.png)



✔ **RecylerView**

```xml
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/calenderRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```



✔ create New Resource File in layout

**calendar_cell.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/cellDayText"
        android:text="1"
        android:textSize="20sp"
        android:textColor="@color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintVertical_bias="0.25"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```



✔ create new java file where MainActivity.java file located

**CalendarAdapter.java**

extends Recycleview.Adapter and type CalendarViewHolder

❗ implement with red bold light sign

![image-20220918163228779](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918163228779.png)

```java
package com.example.calendarapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
```



**CalendarViewHolder.java**

❗ create constructor matching super with red bold light sign

```java
package com.example.calendarapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
```



declare TextView dayOfMonth

```java
package com.example.calendarapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    private final TextView dayOfMonth;

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
    }
}
```



**CalendarAdapter.java**

declare ArrayList<String> daysOfMonth;

create Constructor

![image-20220918164021681](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918164021681.png)

```java
package com.example.calendarapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> dayOfMonth;

    public CalendarAdapter(ArrayList<String> dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
```



✔inflater

**CalendarViewHolder onCreateViewHolder**

```java
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 1.66666666);
        return new CalendarViewHolder(view);
    }
```



**CalendarViewHolder.java**

Make TextView dayOfMonth public

`public final TextView dayOfMonth;`



**CalendarViewAdapter.java - onBindViewHolder**

```java
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(dayOfMonth.get(position));
    }
```



**CalendarViewAdapter.java - getItemCount**

```java
    @Override
    public int getItemCount() {
        return dayOfMonth.size();
    }
```



✔ create interface

**CalendarApapter.java**

```java
    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
```

declare onItemListener

```java
    private final ArrayList<String> dayOfMonth;
    private final OnItemListener onItemListener;
```

❗ Add constructor parameter with bold light sign

Then, code added

```
    public CalendarAdapter(ArrayList<String> dayOfMonth, OnItemListener onItemListener) {
        this.dayOfMonth = dayOfMonth;
        this.onItemListener = onItemListener;
    }
```



**CalendarViewHolder.java**

declare onItemListener

❗ Add constructor parameter with bold light sign

![image-20220918200212981](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918200212981.png)

```java
package com.example.calendarapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
    }
}
```



add `itemView.setonClickListener(this);`

ViewHolder implements View.OnClickListener

❗ implement methods with red bold light sign

![image-20220918200545665](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918200545665.png)



**onClick**

```java
    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
```



**MainActivity.java**

```java
package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void previousMonthAction(View view) {
    }

    public void nextMonthAction(View view) {
    }
}
```



**➡Declare**

* `private TextView monthYearText;`
* `private RecyclerView calendarRecyclerView;`
* `private LocalDate selectedDate;`

**➡onCreate**

* create methods called 'initWidgets()'
* ❗ create methods with red bold light sign

**➡initWdgets**

```java
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calenderRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }
```

* `selectedDate = LocalDate.now();`

**➡setMonthView**

* create methods called 'setMonthView()'

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
```

* add `ArrayList<String> daysInMonth = dayInMonthArray(selectedDate);`

**➡dayInMonthArray**

* create methods called 'dayInMonthArray()'

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }
```

**➡setMonthView**

* add `CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);`
* add `public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener`

* ❗ implement methods with red bold light sign

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = dayInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }
```

**➡previousMonthAction**

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }
```

**➡nextMonthAction**

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }
```

**➡onItemClick**

```java
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Select Date" + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
```



**activity_main.xml**

Extract Resource

* `android:text="<-"`  into `android:text="@string/back"`
* `android:text="->"`  into `android:text="@string/forward"`

![image-20220918224237405](C:\Users\multicampus\Desktop\Git\S07P22B103\TIL\최수연\AndroidStudio_Calendar.assets\image-20220918224237405.png)