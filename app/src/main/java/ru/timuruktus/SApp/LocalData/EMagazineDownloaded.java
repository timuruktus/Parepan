package ru.timuruktus.SApp.LocalData;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.MagazinePart.Magazine;


public class EMagazineDownloaded implements BaseEvent {


    private String downloadedPath;
    private boolean PDF;
    private Magazine magazine;

    public EMagazineDownloaded(String downloadedPath, boolean PDF, Magazine magazine) {
        this.downloadedPath = downloadedPath;
        this.PDF = PDF;
        this.magazine = magazine;
    }

    public String getDownloadedPath() {
        return downloadedPath;
    }

    public boolean isPDF() {
        return PDF;
    }

    public Magazine getMagazine() {
        return magazine;
    }




    @Override
    public void callback() {

    }
}
