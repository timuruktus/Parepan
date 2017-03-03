package ru.timuruktus.SApp.MainPart;

import android.app.Fragment;

import ru.timuruktus.SApp.BaseEvent;

public class EChangeFragment implements BaseEvent {



    private Fragment fragment;
    private boolean addToBackStack;

    public EChangeFragment(Fragment fragment, boolean addToBackStack){
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }

    public void setAddToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
    }

    @Override
    public void callback() {

    }
}
