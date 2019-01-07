package com.example.demo1.rxjavaandretrofit.movie;

import java.io.Serializable;

public class Movie implements Serializable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
