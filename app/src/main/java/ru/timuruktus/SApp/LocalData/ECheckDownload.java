package ru.timuruktus.SApp.LocalData;

import android.widget.Button;

import ru.timuruktus.SApp.BaseEvent;


public class ECheckDownload implements BaseEvent {
    private Object object;
    private String objectId;
    private Button b;
    private CheckWhat checkWhat;

    public ECheckDownload(Object object, String objectId, Button b, CheckWhat checkWhat) {
        this.object = object;
        this.objectId = objectId;
        this.b = b;
        this.checkWhat = checkWhat;
    }

    public enum CheckWhat{
        PDF,Text
    }


    @Override
    public void callback() {

    }

    public CheckWhat getCheckWhat() {
        return checkWhat;
    }

    public void setCheckWhat(CheckWhat checkWhat) {
        this.checkWhat = checkWhat;
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
