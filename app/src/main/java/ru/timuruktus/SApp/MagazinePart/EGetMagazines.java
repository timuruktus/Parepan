package ru.timuruktus.SApp.MagazinePart;

import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;

public class EGetMagazines implements BaseEvent {

    public ArrayList<Magazine> magazines;
    public BaseFragment fragment;


    public EGetMagazines(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }

    @Override
    public void callback() {
        fragment.eventCallback(this);
    }
}
