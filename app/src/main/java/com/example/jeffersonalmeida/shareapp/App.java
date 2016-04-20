package com.example.jeffersonalmeida.shareapp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by jeffersonalmeida on 4/18/16.
 */
public class App extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Hja3Xp6TdURu607GAdNBXRI0F";
    private static final String TWITTER_SECRET = "9KT1XRFl6TzPHaGGz4VM8vSq7ML6Ws2yvjFNlEKam9z9yRfmST";


    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));

        FacebookSdk.sdkInitialize(getApplicationContext());

    }
}


