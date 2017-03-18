package ru.timuruktus.SApp.TextPart;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;
import ru.timuruktus.SApp.MagazinePart.Magazine;


public class TextPresenter implements BasePresenter {

    Magazine magazine;

    public TextPresenter(){

    }

    public TextPresenter(Magazine magazine){
        this.magazine = magazine;

    }


    @Override
    public void eventCallback(BaseEvent event) {

    }
}
