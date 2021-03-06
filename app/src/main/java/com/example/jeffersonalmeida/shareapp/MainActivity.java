package com.example.jeffersonalmeida.shareapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.PlusShare;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.share_fb)
    ShareButton shareFb;
    @Bind(R.id.share_google_plus)
    Button shareGooglePlus;
    @Bind(R.id.share_twitter)
    Button shareTwitter;
    @Bind(R.id.crash)
    Button crash;
    @Bind(R.id.go2Map)
    Button go2Map;
    private ShareButton shareButtonFacebook;
    private Button shareButtonGooglePlus;

    private Button shareButtonTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shareButtonGooglePlus = (Button) findViewById(R.id.share_google_plus);
        shareButtonGooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWithGooglePlus();
            }
        });

        shareButtonTwitter = (Button) findViewById(R.id.share_twitter);
        shareButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWithTwitter();
            }
        });

        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });


    }

    private void shareWithTwitter() {
        TweetComposer.Builder tweetComposer = new TweetComposer.Builder(this);
        try {
            URL url = new URL("https://developers.google.com/+/");
            tweetComposer.url(url);

            tweetComposer.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void shareWithGooglePlus() {
        Log.d("Share", "shareWithGooglePlus");

        // Launch the Google+ share dialog with attribution to your app.
        Intent shareIntent = new PlusShare.Builder(this)
                .setType("text/plain")
                .setText("Welcome to the Google+ platform.")
                .setContentUrl(Uri.parse("https://developers.google.com/+/"))
                .getIntent();

        startActivityForResult(shareIntent, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void forceCrash(View view) {
        Log.d("forceCrash", "forceCrash");
//        throw new RuntimeException("This is a crash");
    }


    public void shareWitFaceBook(View view) {
        Log.d("Share", "shareWitFaceBook");
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();

        shareButtonFacebook.setShareContent(content);

        ShareDialog.show(this, content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Shared", Toast.LENGTH_LONG).show();
        }
    }

    private void share() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://g1.globo.com/");
        startActivityForResult(shareIntent, 222);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.share_fb, R.id.share_google_plus, R.id.share_twitter, R.id.crash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_fb:
                break;
            case R.id.share_google_plus:
                break;
            case R.id.share_twitter:
                break;
            case R.id.crash:
                break;
        }
    }

    @OnClick(R.id.go2Map)
    public void onClick() {
        startActivity(new Intent(this, MapActivity.class));
    }
}
