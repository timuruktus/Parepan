package ru.timuruktus.SApp.BackendlessPart;

import ru.timuruktus.SApp.BaseEvent;


public class EIncreaseDownloads implements BaseEvent {

    String objectId;

    public EIncreaseDownloads(String objectId) {
        this.objectId = objectId;
    }


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    @Override
    public void callback() {

    }
}
