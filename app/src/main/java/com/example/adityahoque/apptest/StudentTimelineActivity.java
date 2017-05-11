package com.example.adityahoque.apptest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import java.util.List;

import retrofit2.Call;

public class StudentTimelineActivity extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_timeline);

        final Context context = this;

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
// Can also use Twitter directly: Twitter.getApiClient()
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.homeTimeline(200,null,null,null,null,null,null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result)
            {

                final FixedTweetTimeline homeTimeline = new FixedTweetTimeline.Builder()
                        .setTweets(result.data)
                        .build();

                final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(context)
                        .setTimeline(homeTimeline)
                        .build();
                ListView listView = (ListView) findViewById(R.id.timeline);
                listView.setAdapter(adapter);

            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });

    }

    public void homeButtonClick(View v) {
            startActivity(new Intent(StudentTimelineActivity.this, StudentTimelineActivity.class));
        }

    public void searchButtonClick(View v)
    {
        startActivity(new Intent(StudentTimelineActivity.this, SearchActivity.class));
    }



}
