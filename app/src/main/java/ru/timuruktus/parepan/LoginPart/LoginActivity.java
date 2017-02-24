package ru.timuruktus.parepan.LoginPart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import ru.timuruktus.parepan.MainPart.MainActivity;
import ru.timuruktus.parepan.R;

public class LoginActivity extends Activity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_SCHOOL = "School";
    // If things goes right
    public static final String APP_PREFERENCES_COUNTRY = "Country";
    SharedPreferences settings;
    ExtendedSliderLayout imageSlider;
    TextView loginChooseText;
    ListView loginCitiesList;

    private static final String[] CITIES = {
            "Моего города нет в списке", "Екатеринбург",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String city = settings.getString(APP_PREFERENCES_CITY, "");
        String school = settings.getString(APP_PREFERENCES_SCHOOL, "");

        if(city.equals("") || settings.getString(APP_PREFERENCES_CITY, "").equals("")){
            // User didn't choose his city and school
            loadAllElements();
        }else{
            // User has already choose his login
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("City", city);
            intent.putExtra("School", school);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_from, R.anim.alpha_to);
        }
    }

    void loadAllElements(){
        imageSlider = (ExtendedSliderLayout) findViewById(R.id.slider);
        loginChooseText = (TextView) findViewById(R.id.loginChooseText);
        loginCitiesList = (ListView) findViewById(R.id.loginCitiesList);

        ArrayList<Integer> files = new ArrayList<>();
        files.add(R.drawable.img1);
        files.add(R.drawable.img2);
        files.add(R.drawable.img3);


        for(int i = 0; i < files.size(); i++){
            // initialize a SliderLayout
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(files.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            imageSlider.addSlider(sliderView);
        }
        imageSlider.stopAutoCycle();
        imageSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        imageSlider.setActivated(false);
        imageSlider.getPagerIndicator().setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
