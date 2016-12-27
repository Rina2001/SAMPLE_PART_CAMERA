package com.test.facebooksample;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.*;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity {
    private TextView info;
    private ImageView imageView;
    private LoginButton button;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get initiallize facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        callbackManager = CallbackManager.Factory.create();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.test.facebooksample",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }  catch (Exception e) {

        }
        info = (TextView)findViewById(R.id.info);
        button = (LoginButton)findViewById(R.id.login_button);
     //   imageView = (ImageView)findViewById(R.id.imageView);
        profilePictureView  = (ProfilePictureView)findViewById(R.id.ppView);
        //return token key from facebook By class loginResult
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();

                info.setText(profile.getName());
                profilePictureView.setProfileId(profile.getId());
               /* Picasso.with(getApplicationContext())
                        .load(profile.getProfilePictureUri(200,200))
                        .into(imageView);*/
                Bitmap bitmap = getFacebookProfilePicture(profile.getId());
            }
            @Override public void onCancel() {}
            @Override public void onError(FacebookException error) {}
        });

    }

    public static Bitmap getFacebookProfilePicture(String userID){
        URL imageURL = null;
        Bitmap bitmap = null;
        try {
            imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");

            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            Log.d("getFacebook", "getFacebookProfilePicture: "+ imageURL.openConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    /**
     * @Mehtod onActivityResult
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

     /*
*/
}
