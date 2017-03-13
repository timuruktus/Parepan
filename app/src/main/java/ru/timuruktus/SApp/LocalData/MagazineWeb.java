package ru.timuruktus.SApp.LocalData;

import com.orm.SugarRecord;

import java.util.Date;

public class MagazineWeb extends SugarRecord {

    String city;
    String school;
    String textUrl;
    String pdfUrl;
    String title;
    String titleImage;
    String createdTime;
    String preview;
    String titleSchool;
    Date created;
    int viewsCount;
    String objectId;


    public MagazineWeb() {
    }

    public MagazineWeb(String city, String school, String textUrl, String pdfUrl,
                       String title, String titleImage, String createdTime, String preview,
                       String titleSchool, Date created, int viewsCount, String objectId) {
        this.city = city;
        this.school = school;
        this.textUrl = textUrl;
        this.pdfUrl = pdfUrl;
        this.title = title;
        this.titleImage = titleImage;
        this.createdTime = createdTime;
        this.preview = preview;
        this.titleSchool = titleSchool;
        this.created = created;
        this.viewsCount = viewsCount;
        this.objectId = objectId;
    }
}
