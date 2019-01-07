package com.example.demo1.rxjavaandretrofit.movie;


import java.io.Serializable;
import java.util.List;

public class MovieSubject implements Serializable {
    private String title;
    private List<Movie> subjects;

    public List<Movie> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Movie> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
