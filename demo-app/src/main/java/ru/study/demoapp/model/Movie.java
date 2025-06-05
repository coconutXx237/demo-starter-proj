package ru.study.demoapp.model;

import java.io.Serializable;

public class Movie implements Serializable {
/*    @Serial
    private static final long serialVersionUID = 1L;*/

    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public Movie() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" + "title='" + title + '\'' + '}';
    }
}
