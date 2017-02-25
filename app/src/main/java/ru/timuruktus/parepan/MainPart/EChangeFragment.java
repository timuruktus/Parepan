package ru.timuruktus.parepan.MainPart;

import android.app.Fragment;

public class EChangeFragment {



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
}
