package ru.timuruktus.SApp.LocalData;

import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.MagazinePart.Magazine;


public class ESaveMagazines implements BaseEvent {
    ArrayList<Magazine> magazines = new ArrayList<>();

    public ESaveMagazines(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }



    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(ArrayList<Magazine> magazines) {
        this.magazines = magazines;
    }


    @Override
    public void callback() {

    }
}
