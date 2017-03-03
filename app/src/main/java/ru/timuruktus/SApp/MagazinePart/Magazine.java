package ru.timuruktus.SApp.MagazinePart;

public class Magazine {

    private String textUrl;
    private String title;
    private String titleImage;
    private String createdTime;
    private String preview;

    public Magazine() {}

    public Magazine(String textUrl, String title, String titleImage, String createdTime) {
        this.textUrl = textUrl;
        this.title = title;
        this.titleImage = titleImage;
        this.createdTime = createdTime;
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
}
