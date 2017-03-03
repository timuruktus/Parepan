package ru.timuruktus.SApp.MagazinePart;


import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;

public class EGetUserLoginInfo implements BaseEvent {

    private String city, school;
    private BaseFragment baseFragment;

    public EGetUserLoginInfo(){}

    public EGetUserLoginInfo(String city, String school, BaseFragment baseFragment) {
        this.city = city;
        this.school = school;
        this.baseFragment = baseFragment;
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
    public void callback(){
        baseFragment.eventCallback(this);
    }
}
