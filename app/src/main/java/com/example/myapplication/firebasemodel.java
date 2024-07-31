package com.example.myapplication;


public class firebasemodel {

    public String title ;
    public String content;

    public firebasemodel() {
    }

    public  String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  String getContent() {
        return content;
    }

    public firebasemodel(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

