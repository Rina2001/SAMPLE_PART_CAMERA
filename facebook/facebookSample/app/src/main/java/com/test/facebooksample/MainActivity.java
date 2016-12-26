package com.test.facebooksample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
    private TextView info;
    private ImageView imageView;
    private LoginButton button;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get initiallize facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();


        info = (TextView)findViewById(R.id.info);
        button = (LoginButton)findViewById(R.id.login_button);
        imageView = (ImageView)findViewById(R.id.imageView);

     /*   try {
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
*/
        //return token key from facebook By class loginResult
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();

                info.setText(profile.getName());
                Picasso.with(getApplicationContext())
                        .load(profile.getProfilePictureUri(200,200))
                        .into(imageView);

            }
            @Override public void onCancel() {}
            @Override public void onError(FacebookException error) {}
        });

    }

    /**
     * @Mehtod onActivityResult
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
