package ru.timuruktus.SApp.LoginPart;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;

public class ELoginConfirmed implements BaseEvent {

    public String city, school;
    public BaseFragment baseFragment;

    public ELoginConfirmed(String city, String school, LoginFragment baseFragment) {
        this.city = city;
        this.school = school;
        this.baseFragment = baseFragment;
    }

    @Override
    public void callback(){
        baseFragment.eventCallback(this);
    }

}
