package com.example.adityahoque.apptest;

/**
 * Created by Gia on 4/18/2017.
 */
import android.net.Uri;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;
public class ComposeTweet {

    private Uri myImageUri;
    TweetComposer.Builder builder = new TweetComposer.Builder(this)
            .text("just setting up my Fabric.")
            .image(myImageUri);
builder.show();
}
