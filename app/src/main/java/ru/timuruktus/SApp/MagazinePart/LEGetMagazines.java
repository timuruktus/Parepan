package ru.timuruktus.SApp.MagazinePart;

import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;

public class LEGetMagazines implements BaseEvent {

    public ArrayList<Magazine> magazines;
    public BaseFragment fragment;
    public boolean fromWeb;


    public LEGetMagazines(BaseFragment fragment, boolean fromWeb) {
        this.fragment = fragment;
        this.fromWeb = fromWeb;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }

    public boolean isFromWeb() {
        return fromWeb;
    }

    public void setFromWeb(boolean fromWeb) {
        this.fromWeb = fromWeb;
    }


    @Override
    public void callback() {
        fragment.eventCallback(this);
    }
}
