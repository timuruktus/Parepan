package ru.timuruktus.SApp.MagazinePart;

import java.util.Date;

public class Magazine {

    private String city;
    private String school;
    private String textUrl;
    private String pdfUrl;
    private String title;
    private String titleImage;
    private String createdTime;
    private String preview;
    private String titleSchool;
    private Date created;
    private int viewsCount;
    private boolean downloadedPDF;
    private String objectId;
    private boolean downloadedText;

    public Magazine() {}

    public Magazine(String textUrl, String title, String titleImage, String createdTime,
                    int viewsCount, String titleSchool, Date created, String city, String school,
                    boolean downloadedPDF, String objectId, boolean downloadedText, String pdfUrl) {
        this.textUrl = textUrl;
        this.title = title;
        this.titleImage = titleImage;
        this.createdTime = createdTime;
        this.viewsCount = viewsCount;
        this.titleSchool = titleSchool;
        this.created = created;
        this.city = city;
        this.school = school;
        this.downloadedPDF = downloadedPDF;
        this.objectId = objectId;
        this.downloadedText = downloadedText;
        this.pdfUrl = pdfUrl;
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

    public Date getСreated() {
        return created;
    }

    public void setСreated(Date created) {
        this.created = created;
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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isDownloadedPDF() {
        return downloadedPDF;
    }

    public void setDownloadedPDF(boolean downloadedPDF) {
        this.downloadedPDF = downloadedPDF;
    }

    public boolean isDownloadedText() {
        return downloadedText;
    }

    public void setDownloadedText(boolean downloadedText) {
        this.downloadedText = downloadedText;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }














}
