package ru.timuruktus.parepan.MainPart;

import android.app.Fragment;

public class EOnFragmentChanged {

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
}
