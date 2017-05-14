package com.example.adityahoque.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class PostAssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_timeline);


        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();//get the current Twitter session
        String hashtag = "#"+session.getUserName();//prefill the text field with #username
        final Intent intent = new ComposerActivity.Builder(PostAssignmentActivity.this).hashtags(hashtag)
                .session(session)
                .createIntent();
        startActivity(intent);
    }

    public void homeButtonClick(View v) {
        startActivity(new Intent(PostAssignmentActivity.this, TeacherTimelineActivity.class));//home button takes them back to home timeline
    }

    public void postButtonClick(View v)
    {
        startActivity(new Intent(PostAssignmentActivity.this, PostAssignmentActivity.class));//post button takes them back to post screen
    }
}
