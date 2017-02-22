package ru.timuruktus.parepan.LoginPart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.parepan.MainPart.MainActivity;
import ru.timuruktus.parepan.R;

public class LoginActivity extends Activity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_SCHOOL = "School";
    // If things goes right
    public static final String APP_PREFERENCES_COUNTRY = "Country";
    SharedPreferences settings;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        videoView = (VideoView) findViewById(R.id.videoView);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String city = settings.getString(APP_PREFERENCES_CITY, "");
        String school = settings.getString(APP_PREFERENCES_SCHOOL, "");

        if(city.equals("") || settings.getString(APP_PREFERENCES_CITY, "").equals("")){
            // User didn't choose his city and school
            
        }else{
            // User has already choose his login
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("City", city);
            intent.putExtra("School", school);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_from, R.anim.alpha_to);
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
    }
}
