package ru.timuruktus.SApp.LocalData;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.MagazinePart.Magazine;


public class EMagazineDownloaded implements BaseEvent {


    private String downloadedPath;
    private boolean isPDF;
    private Magazine magazine;

    public EMagazineDownloaded(String downloadedPath, boolean isPDF, Magazine magazine) {
        this.downloadedPath = downloadedPath;
        this.isPDF = isPDF;
        this.magazine = magazine;
    }

    public String getDownloadedPath() {
        return downloadedPath;
    }

    public boolean isPDF() {
        return isPDF;
    }

    public Magazine getMagazine() {
        return magazine;
    }




    @Override
    public void callback() {

    }
}
