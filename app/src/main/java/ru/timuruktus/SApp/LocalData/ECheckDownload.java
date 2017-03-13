package ru.timuruktus.SApp.LocalData;

import android.widget.Button;

import ru.timuruktus.SApp.BaseEvent;


public class ECheckDownload implements BaseEvent {
    private Object object;
    private String objectId;
    private Button b;

    public ECheckDownload(Object object, String objectId, Button b) {
        this.object = object;
        this.objectId = objectId;
        this.b = b;
    }

    @Override
    public void callback() {

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Button getB() {
        return b;
    }

    public void setB(Button b) {
        this.b = b;
    }




}
