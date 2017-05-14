package com.example.adityahoque.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yc90bqeauRoMtXvQlIdbccVKm";//given by Twitter
    private static final String TWITTER_SECRET = "BmZlIcqRaT1KL3wHNzMjRjLBb9A8yHYmLvPOEri6lsCts7LwVY";//given by Twitter



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);//authenticate Twitter
        Fabric.with(this, new Twitter(authConfig), new TweetComposer());//allow Fabric to work
        setContentView(R.layout.activity_login_screen);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);//instantiate Login button
        loginButton.setCallback(new Callback<TwitterSession>()
        {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;//Twitter session
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in!";//message to print at bottom
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();//show the message
                startActivity(new Intent(getApplicationContext(), TeacherOrStudentActivity.class));//go to asking if student or teacher
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }


        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


}

