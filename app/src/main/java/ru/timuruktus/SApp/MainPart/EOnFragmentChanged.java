package ru.timuruktus.SApp.MainPart;

import android.app.Fragment;

import ru.timuruktus.SApp.BaseEvent;

public class EOnFragmentChanged implements BaseEvent {

    private Fragment fragment;


    public EOnFragmentChanged(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void callback() {

    }
}
