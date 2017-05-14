package com.example.adityahoque.apptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeacherOrStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_or_student);
    }

    public void studentButtonClick(View v) {
        startActivity(new Intent(TeacherOrStudentActivity.this, StudentTimelineActivity.class));//student button brings them to student timeline screen
    }

    public void teacherButtonClick(View v) {
        startActivity(new Intent(TeacherOrStudentActivity.this, TeacherTimelineActivity.class));//teacher button brings them to teacher timeline screen
    }

}

