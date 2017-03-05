package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ru.timuruktus.SApp.BackendlessPart.BackendlessMagazines;
import ru.timuruktus.SApp.BackendlessPart.EGetMagazinesList;
import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;

import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_CITY;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_SCHOOL;

public class MagazinePresenter implements BasePresenter {

    MagazineFragment magazineFragment;
    SharedPreferences settings;
    EGetMagazines eGetMagazines;

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
    public void getMagazines(EGetMagazines event){
        BackendlessMagazines backendlessMagazines = new BackendlessMagazines();
        String whereClause = "city = '" + getCity() + "' and school = '"
                + getSchool() + "'";
        this.eGetMagazines = event;
        EventBus.getDefault().post(new EGetMagazinesList(whereClause, this));
    }

    @Override
    public void eventCallback(BaseEvent event) {
        if(event instanceof EGetMagazinesList){
            List<Magazine> magazineList = ((EGetMagazinesList) event).getMagazines();
            ArrayList<Magazine> magazineArrayList = (ArrayList<Magazine>) magazineList;
            eGetMagazines.setMagazines(magazineArrayList);
            eGetMagazines.callback();
        }
    }
}