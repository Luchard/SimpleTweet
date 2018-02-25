package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luchi on 2/23/2018.
 */

public class Tweet {
    // list out
    public String body;
    public long uid;
    public User user;
    public String createdAt;

    public static Tweet fromJson(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.createdAt = jsonObject.getString("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  tweet;
    }
}
