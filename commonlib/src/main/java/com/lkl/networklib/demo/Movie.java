package com.lkl.networklib.demo;

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
