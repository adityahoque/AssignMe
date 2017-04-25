package com.example.adityahoque.apptest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.List;

import retrofit2.Call;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


    }
    public void generateSearchResult(View v)
    {
        final Context context = this;

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
// Can also use Twitter directly: Twitter.getApiClient()

        SearchService searchService = twitterApiClient.getSearchService();
        EditText searchText = (EditText) findViewById(R.id.searchBar);
        Call<Search> call = searchService.tweets("@nfl",null,null,null,null,100,null,null,null,true);
        call.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result)
            {
                final FixedTweetTimeline homeTimeline = new FixedTweetTimeline.Builder()
                        .setTweets(result.data.tweets)
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
}
