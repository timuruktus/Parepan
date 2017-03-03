package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_CITY;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_SCHOOL;

public class MagazinePresenter {

    MagazineFragment magazineFragment;
    SharedPreferences settings;

    public MagazinePresenter(MagazineFragment magazineFragment) {
        this.magazineFragment = magazineFragment;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getUserLoginInfo(EGetUserLoginInfo event){
        settings = magazineFragment.getActivity().getSharedPreferences(APP_PREFERENCES,
                Context.MODE_PRIVATE);
        String city = settings.getString(APP_PREFERENCES_CITY, "");
        String school = settings.getString(APP_PREFERENCES_SCHOOL, "");
        event.setCity(city);
        event.setSchool(school);
        event.callback();
    }
}
