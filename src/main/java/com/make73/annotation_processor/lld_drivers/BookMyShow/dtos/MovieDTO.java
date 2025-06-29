package com.make73.annotation_processor.lld_drivers.BookMyShow.dtos;

public class MovieDTO {
    private int id;
    private String name;

    public MovieDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
