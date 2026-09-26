<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="16dp">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Text to Speech Converter"
android:textSize="22sp"
android:textStyle="bold"
android:paddingBottom="20dp"/>
<EditText
android:id="@+id/etText"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="Enter text to speak"
android:minLines="4"
android:gravity="top"
android:inputType="textMultiLine"/>

<Button
android:id="@+id/btnSpeak"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="20dp"
android:text="Speak"/>
<Button
android:id="@+id/btnStop"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="10dp"
android:text="Stop"/>
</LinearLayout>
