package ru.timuruktus.SApp.LocalData;

import ru.timuruktus.SApp.BaseEvent;


public class ECheckTextDownload implements BaseEvent {
    public Object object;
    public String objectId;

    @Override
    public void callback() {

    }

    public ECheckTextDownload(Object object, String objectId){
        this.object = object;
        this.objectId = objectId;
    }
}
