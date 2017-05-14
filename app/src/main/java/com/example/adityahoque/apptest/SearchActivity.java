package com.example.adityahoque.apptest;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.models.UserBuilder;
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

        EditText searchEditText = (EditText) findViewById(R.id.searchBar); //get the search bar
        String searchText = searchEditText.getText().toString(); //get the text in the search bar
        SearchService searchService = twitterApiClient.getSearchService();

        Call<Search> call = searchService.tweets(searchText,null,null,null,null,100,null,null,null,true);//gets 100 results (max)
        call.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result)
            {
                final FixedTweetTimeline homeTimeline = new FixedTweetTimeline.Builder()
                        .setTweets(result.data.tweets)
                        .build();//instantiate the search timeline

                final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(context)
                        .setTimeline(homeTimeline)
                        .build();//show the search timeline
                ListView listView = (ListView) findViewById(R.id.searchResult);
                listView.setAdapter(adapter);

            }

            public void failure(TwitterException exception) {
                //Do something on failure
            }
        });


    }
    public void homeButtonClick(View v) {
        startActivity(new Intent(SearchActivity.this, StudentTimelineActivity.class));//home button takes them back to home timeline
    }

    public void searchButtonClick(View v)
    {
        startActivity(new Intent(SearchActivity.this, SearchActivity.class));//search button takes them back to search screen
    }
}
