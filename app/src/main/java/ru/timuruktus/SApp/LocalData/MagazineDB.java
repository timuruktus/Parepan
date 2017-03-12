package ru.timuruktus.SApp.LocalData;

import com.orm.SugarRecord;

public class MagazineDB extends SugarRecord {

    String city;
    String pathToPDF;
    String pathToText;
    String pathToTitleImage;
    String pathsToTextImages;
    String preview;
    String school;
    String title;
    String objectId;

    public MagazineDB() {
    }

    public MagazineDB(String city, String pathToPDF, String pathToText, String pathToTitleImage,
                      String pathsToTextImages, String preview, String school, String title,
                      String objectId) {
        this.city = city;
        this.pathToPDF = pathToPDF;
        this.pathToText = pathToText;
        this.pathToTitleImage = pathToTitleImage;
        this.pathsToTextImages = pathsToTextImages;
        this.preview = preview;
        this.school = school;
        this.title = title;
        this.objectId = objectId;
    }
}
