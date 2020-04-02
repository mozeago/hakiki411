package com.mosesasiago.hakiki111.models;

public class News {
    private String title;
    private int id;
    private String summary;
    private String category;
    private String imageUrl;
    private String sourceUrl;
    private String points;
    private String created_at;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPoints() {
        return points;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }
}
