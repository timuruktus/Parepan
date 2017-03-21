package ru.timuruktus.SApp.LocalData;

import ru.timuruktus.SApp.BaseEvent;


public class EClearDownloadedMagazine implements BaseEvent {


    private String path;
    private boolean isPDF;

    public EClearDownloadedMagazine(String path, boolean isPDF) {
        this.path = path;
        this.isPDF = isPDF;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPDF() {
        return isPDF;
    }

    public void setPDF(boolean PDF) {
        isPDF = PDF;
    }

    @Override
    public void callback() {

    }
}
