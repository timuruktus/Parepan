package ru.timuruktus.SApp.MagazinePart;

import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;

public class EGetMagazines implements BaseEvent {


    public EGetMagazines(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }

    public ArrayList<Magazine> magazines;
    public BaseFragment fragment;

    @Override
    public void callback() {
        fragment.eventCallback(this);
    }
}
