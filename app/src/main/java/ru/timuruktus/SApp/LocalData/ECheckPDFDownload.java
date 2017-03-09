package ru.timuruktus.SApp.LocalData;

import ru.timuruktus.SApp.BaseEvent;


public class ECheckPDFDownload implements BaseEvent {
    public Object object;
    public String objectId;

    @Override
    public void callback() {

    }

    public ECheckPDFDownload(Object object, String objectId){
        this.object = object;
        this.objectId = objectId;
    }
}
