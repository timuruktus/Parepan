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
import ru.timuruktus.SApp.LocalData.EGetMagazinesDB;
import ru.timuruktus.SApp.LocalData.ESaveMagazines;

import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_CITY;
import static ru.timuruktus.SApp.MainPart.MainPresenter.APP_PREFERENCES_SCHOOL;

public class MagazinePresenter implements BasePresenter {

    private MagazineFragment magazineFragment;
    private SharedPreferences settings;
    private LEGetMagazines lEGetMagazines;

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
        this.lEGetMagazines = event;
        if(!event.isFromWeb()) {
            EventBus.getDefault().post(new EGetMagazinesDB(getCity(), getSchool(), this));
        }else{
            EventBus.getDefault().post(new EGetMagazinesWeb(getWhereClause(getCity(), getSchool()), this));
        }
    }

    @Override
    public void eventCallback(BaseEvent event) {
        List<Magazine> magazineList;
        ArrayList<Magazine> magazineArrayList;
        if(event instanceof EGetMagazinesDB){
            EGetMagazinesDB currentEvent = (EGetMagazinesDB) event;
            magazineList = currentEvent.getMagazines();
            magazineArrayList = (ArrayList<Magazine>) magazineList;
            lEGetMagazines.setMagazines(magazineArrayList);
            lEGetMagazines.callback();
        }else if(event instanceof EGetMagazinesWeb){
            EGetMagazinesWeb currentEvent = (EGetMagazinesWeb) event;
            magazineList = currentEvent.getMagazines();
            magazineArrayList = (ArrayList<Magazine>) magazineList;
            saveMagazinesInDB(magazineArrayList);
            lEGetMagazines.setMagazines(magazineArrayList);
            lEGetMagazines.callback();
        }
    }

    private String getWhereClause(String city, String school){
        return "city = '" + city + "' and school = '"
                + school + "'";
    }

    private void saveMagazinesInDB(ArrayList<Magazine> magazines){
        EventBus.getDefault().post(new ESaveMagazines(magazines));
    }


    public void detachListeners(){
        EventBus.getDefault().unregister(this);
    }

}
