package com.codepath.apps.restclienttemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetActivity extends AppCompatActivity {
    private Tweet mContact;
    private ImageView ivProfile;
    private TextView tvName;
    private TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        ivProfile = (ImageView) findViewById(R.id.ivProfileImage);
        tvName = (TextView) findViewById(R.id.tvUserName);
        tvPhone = (TextView) findViewById(R.id.tvBody);
       // vPalette = findViewById(R.id.vPalette);

        // Extract tweet from bundle
        Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweet"));

        // Fill views with data
        Glide.with(TweetActivity.this).load(tweet.user.profileImageUrl).centerCrop().into(ivProfile);
        tvName.setText(tweet.user.name);
        tvPhone.setText(tweet.body);
    }
}
