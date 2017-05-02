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
        startActivity(new Intent(TeacherOrStudentActivity.this, StudentTimelineActivity.class));
    }

    public void teacherButtonClick(View v) {
        startActivity(new Intent(TeacherOrStudentActivity.this, TeacherTimelineActivity.class));
    }

}

