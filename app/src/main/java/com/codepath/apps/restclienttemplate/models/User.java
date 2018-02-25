package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luchi on 2/23/2018.
 */

public class User {

    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    public static  User fromJSON(JSONObject jsonObject) throws  JSONException{
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.screenName =   jsonObject.getString("screen_name");
            user.uid = jsonObject.getLong("id");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
