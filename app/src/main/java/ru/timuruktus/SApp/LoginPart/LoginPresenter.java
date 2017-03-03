package ru.timuruktus.SApp.LoginPart;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.SApp.MainPart.EChangeFragment;
import ru.timuruktus.SApp.WelcomePart.WelcomeFragment;

import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_CITY;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_SCHOOL;

public class LoginPresenter {

    private Fragment fragment;
    SharedPreferences settings;

    public LoginPresenter(Fragment fragment) {
        this.fragment = fragment;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onLoginConfrimed(ELoginConfirmed event){
        settings = fragment.getActivity().getSharedPreferences(APP_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString(APP_PREFERENCES_CITY, event.city);
        ed.putString(APP_PREFERENCES_SCHOOL, event.school);
        event.callback();
        ed.apply();
        Log.d("LoginPresenter", settings.getString(APP_PREFERENCES_CITY, ""));
        Log.d("LoginPresenter", settings.getString(APP_PREFERENCES_SCHOOL, ""));
        EventBus.getDefault().post(new EChangeFragment(new WelcomeFragment(), false));
    }



}
