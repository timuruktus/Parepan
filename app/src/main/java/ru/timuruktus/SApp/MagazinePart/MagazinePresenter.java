package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ru.timuruktus.SApp.BackendlessPart.EGetMagazinesWeb;
import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;

import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_CITY;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_SCHOOL;

public class MagazinePresenter implements BasePresenter {

    private MagazineFragment magazineFragment;
    private SharedPreferences settings;
    private LEGetMagazines LEGetMagazines;

    public MagazinePresenter(MagazineFragment magazineFragment) {
        this.magazineFragment = magazineFragment;
        EventBus.getDefault().register(this);
        settings = magazineFragment.getActivity().getSharedPreferences(APP_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    private String getCity(){
        return settings.getString(APP_PREFERENCES_CITY, "");
    }

    private String getSchool(){
        return settings.getString(APP_PREFERENCES_SCHOOL, "");
    }

    @Subscribe
    public void getMagazines(LEGetMagazines event){
        this.LEGetMagazines = event;
        EventBus.getDefault().post(new EGetMagazinesWeb(getWhereClause(), this));
    }

    private String getWhereClause(){
        return "city = '" + getCity() + "' and school = '"
                + getSchool() + "'";
    }

    @Override
    public void eventCallback(BaseEvent event) {
        if(event instanceof EGetMagazinesWeb){
            EGetMagazinesWeb currentEvent = (EGetMagazinesWeb) event;
            List<Magazine> magazineList = currentEvent.getMagazines();
            ArrayList<Magazine> magazineArrayList = (ArrayList<Magazine>) magazineList;
            LEGetMagazines.setMagazines(magazineArrayList);
            LEGetMagazines.callback();
        }
    }

    public void detachListeners(){
        EventBus.getDefault().unregister(this);
    }

}
