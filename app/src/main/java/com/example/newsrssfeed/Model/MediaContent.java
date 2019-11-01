package com.example.newsrssfeed.Model;

public class MediaContent {

    private String width;

    private String medium;

    @Override
    public String toString() {
        return "MediaContent{" +
                "width='" + width + '\'' +
                ", medium='" + medium + '\'' +
                ", url='" + url + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    private String url;

    private String height;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


}
