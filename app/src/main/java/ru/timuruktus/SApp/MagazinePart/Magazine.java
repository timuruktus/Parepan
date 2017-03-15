package ru.timuruktus.SApp.MagazinePart;

import com.orm.SugarRecord;

import java.util.Date;

public class Magazine extends SugarRecord {

    String city;
    String school;
    String textUrl;
    String pdfUrl;
    String downloadedTextPath;
    String downloadedPDFPath;
    String title;
    String titleImage;
    String createdTime;
    String preview;
    String titleSchool;
    Date created;
    int viewsCount;
    boolean downloadedPDF;
    String objectId;
    boolean downloadedText;

    public Magazine() {}

    public Magazine(String city, String school, String textUrl, String pdfUrl,
                    String downloadedTextPath, String downloadedPDFPath, String title,
                    String titleImage, String createdTime, String preview, String titleSchool,
                    Date created, int viewsCount, boolean downloadedPDF, String objectId,
                    boolean downloadedText) {
        this.city = city;
        this.school = school;
        this.textUrl = textUrl;
        this.pdfUrl = pdfUrl;
        this.downloadedTextPath = downloadedTextPath;
        this.downloadedPDFPath = downloadedPDFPath;
        this.title = title;
        this.titleImage = titleImage;
        this.createdTime = createdTime;
        this.preview = preview;
        this.titleSchool = titleSchool;
        this.created = created;
        this.viewsCount = viewsCount;
        this.downloadedPDF = downloadedPDF;
        this.objectId = objectId;
        this.downloadedText = downloadedText;
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

    public String getDownloadedTextPath() {
        return downloadedTextPath;
    }

    public void setDownloadedTextPath(String downloadedTextPath) {
        this.downloadedTextPath = downloadedTextPath;
    }

    public String getDownloadedPDFPath() {
        return downloadedPDFPath;
    }

    public void setDownloadedPDFPath(String downloadedPDFPath) {
        this.downloadedPDFPath = downloadedPDFPath;
    }
}
