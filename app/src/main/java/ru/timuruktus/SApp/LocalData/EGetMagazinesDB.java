package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import java.util.List;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.BasePresenter;
import ru.timuruktus.SApp.MagazinePart.Magazine;

public class EGetMagazinesDB implements BaseEvent {

    private BaseFragment fragment;
    private BasePresenter presenter;
    String city;
    String school;

    private List<Magazine> magazines;


    public EGetMagazinesDB() {
    }

    public EGetMagazinesDB(String city, String school, BasePresenter presenter) {
        this.city = city;
        this.school = school;
        this.presenter = presenter;
    }

    public EGetMagazinesDB(String city, String school, BaseFragment fragment) {
        this.city = city;
        this.school = school;
        this.fragment = fragment;
    }

    public EGetMagazinesDB(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public EGetMagazinesDB(BasePresenter presenter) {
        this.presenter = presenter;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    @Override
    public void callback() {
        Log.d("mytag", "EGetMagazinesDB.callback()");
        if(presenter != null){
            presenter.eventCallback(this);
        }else if(fragment != null){
            fragment.eventCallback(this);
        }
    }
}
