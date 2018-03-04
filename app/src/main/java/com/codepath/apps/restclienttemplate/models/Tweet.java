package com.codepath.apps.restclienttemplate.models;

import com.raizlabs.android.dbflow.annotation.Column;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by luchi on 2/23/2018.
 */
@Parcel
public class Tweet {
    // list out
    public String body;
    public long uid;
    public User user;
    public String createdAt;
    public String tweet_media_url;
    public  String tweet_media_type;

    public static Tweet fromJson(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.createdAt = jsonObject.getString("created_at");

            if (!jsonObject.isNull("extended_entities")) {
                JSONObject entitiesObject = jsonObject.getJSONObject("extended_entities");
                JSONArray mediaArray = entitiesObject.getJSONArray("media");
                if (mediaArray.length() > 0) {
                    JSONObject mediaObject = mediaArray.getJSONObject(0);
                    tweet.tweet_media_url = mediaObject.getString("media_url_https");
                    tweet.tweet_media_type = mediaObject.getString("type");
                    if (tweet.tweet_media_type.equals("video")) {
                        JSONObject video_info = mediaObject.getJSONObject("video_info");
                        JSONArray variantsArray = video_info.getJSONArray("variants");
                        if (variantsArray.length() > 0) {
                            tweet.tweet_media_url = variantsArray.getJSONObject(0).getString("url");
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  tweet;
    }

/*    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_screen_name() {
        return user_screen_name;
    }

    public void setUser_screen_name(String user_screen_name) {
        this.user_screen_name = user_screen_name;
    }

    public String getUser_profile_image_url_https() {
        return user_profile_image_url_https;
    }

    public void setUser_profile_image_url_https(String user_profile_image_url_https) {
        this.user_profile_image_url_https = user_profile_image_url_https;
    }

    public int getType_tweet() {
        return type_tweet;
    }

    public void setType_tweet(int type_tweet) {
        this.type_tweet = type_tweet;
    }*/

    public String getTweet_media_url() {
        return tweet_media_url;
    }

    public void setTweet_media_url(String tweet_media_url) {
        this.tweet_media_url = tweet_media_url;
    }

    public String getTweet_media_type() {
        return tweet_media_type;
    }

    public void setTweet_media_type(String tweet_media_type) {
        this.tweet_media_type = tweet_media_type;
    }
}
