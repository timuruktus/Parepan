package ru.timuruktus.SApp.MainPart;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Set;

import ru.timuruktus.SApp.BaseEvent;

public class EChangeFragment implements BaseEvent {


    private Bundle bundle;
    private Fragment fragment;
    private boolean addToBackStack;

    public EChangeFragment(Fragment fragment, boolean addToBackStack){
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public EChangeFragment(Fragment fragment, boolean addToBackStack,
                                 HashMap<String, String> dataSet) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
        Set<String> keys = dataSet.keySet();
        bundle = new Bundle();
        for(String key : keys) {
            bundle.putString(key, dataSet.get(key));
        }
        fragment.setArguments(bundle);
    }

    public boolean isHaveBundle(){
        return bundle != null;
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

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }


    @Override
    public void callback() {

    }
}
