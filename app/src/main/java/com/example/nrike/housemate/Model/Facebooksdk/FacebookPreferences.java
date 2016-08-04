package com.example.nrike.housemate.Model.Facebooksdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * Created by develapps15 on 4/8/16.
 */
public class FacebookPreferences {

    Context context;

    public FacebookPreferences(Context context) {
        this.context = context;
    }

    public void LoginTrue(){
        SharedPreferences preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("enter_main",true);
        edit.commit();
    }

    public void LoginFalse(){
        SharedPreferences preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("enter_main",false);
        edit.commit();
    }

    public void GetProfileData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                Profile profile = Profile.getCurrentProfile();
                String name = profile.getName();
                String userimage= "" ;
                if (Profile.getCurrentProfile()!=null) {
                    Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                    userimage= String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                }else{
                    Log.i("face","sin foto de perfil");
                    userimage="unknown";
                }
                Log.i("face","name "+name);

                // Application code
                SharedPreferences preferences = context.getSharedPreferences("PREFS",context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("user_name",name);
                edit.putString("user_image_profile",userimage);
                edit.commit();
            }
        });
        request.executeAsync();
    }
}
