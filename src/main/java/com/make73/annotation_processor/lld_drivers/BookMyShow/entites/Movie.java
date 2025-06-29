package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

public class Movie {
    private Integer id;
    private String name;

    public Movie(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
