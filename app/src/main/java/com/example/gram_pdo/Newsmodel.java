package com.example.gram_pdo;

public class Newsmodel {
    String heading, about, detail;

    public Newsmodel() {
    }

    public Newsmodel(String heading, String about, String detail) {
        this.heading = heading;
        this.about = about;
        this.detail = detail;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
