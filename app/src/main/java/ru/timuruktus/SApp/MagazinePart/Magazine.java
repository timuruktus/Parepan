package ru.timuruktus.SApp.MagazinePart;

import java.util.Date;

public class Magazine {

    private String city;
    private String school;
    private String textUrl;
    private String title;
    private String titleImage;
    private String createdTime;
    private String preview;
    private String titleSchool;
    private Date date;
    private int viewsCount;

    public Magazine() {}

    public Magazine(String textUrl, String title, String titleImage, String createdTime,
                    int viewsCount, String titleSchool, Date date, String city, String school) {
        this.textUrl = textUrl;
        this.title = title;
        this.titleImage = titleImage;
        this.createdTime = createdTime;
        this.viewsCount = viewsCount;
        this.titleSchool = titleSchool;
        this.date = date;
        this.city = city;
        this.school = school;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }


    public String getTitleSchool() {
        return titleSchool;
    }

    public void setTitleSchool(String titleSchool) {
        this.titleSchool = titleSchool;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }






}
