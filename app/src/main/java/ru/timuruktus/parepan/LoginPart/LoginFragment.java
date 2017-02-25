package ru.timuruktus.parepan.LoginPart;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.timuruktus.parepan.R;

public class LoginFragment extends Fragment {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_SCHOOL = "School";
    // If things goes right
    public static final String APP_PREFERENCES_COUNTRY = "Country";
    SharedPreferences settings;
    TextView loginChooseText;

    private static final String[] CITIES = {
            "Моего города нет в списке", "Екатеринбург",};

    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.login_fragment, container, false);

        settings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String city = settings.getString(APP_PREFERENCES_CITY, "");
        String school = settings.getString(APP_PREFERENCES_SCHOOL, "");

        return rootView;
    }

    void loadAllElements(){
        loginChooseText = (TextView) rootView.findViewById(R.id.loginChooseText);

    }

    @Override
    public void onResume(){
        super.onResume();
    }

}
