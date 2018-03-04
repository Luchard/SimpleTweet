package com.codepath.apps.restclienttemplate;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {
TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String screenName = getIntent().getStringExtra("screen_name");

        UserTimelineFragment userTimelineFragment =  UserTimelineFragment.newInstance(screenName);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContainer , userTimelineFragment);
        ft.commit();
client = TwitterApp.getRestClient();
client.getUserInfoTimeline(new JsonHttpResponseHandler(){
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        User user = null;
        try {
            user = User.fromJSON(response);
            getSupportActionBar().setTitle(user.screenName);

            PopulateUserHeadline(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
});


    }
public void PopulateUserHeadline(User user){
    TextView tvName = (TextView) findViewById(R.id.tvName);
    TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
    TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
    TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);

    ImageView imageView = (ImageView) findViewById(R.id.ivProfileImage);
    tvName.setText(user.name);
    Glide.with(this).load(user.profileImageUrl).into(imageView);

    tvTagline.setText(user.tagLine);
    tvFollowers.setText(user.followersCount + " Followers");
    tvFollowing.setText(user.followingCount + " Following");
}

}
