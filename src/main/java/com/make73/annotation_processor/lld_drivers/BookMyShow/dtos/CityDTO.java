package com.make73.annotation_processor.lld_drivers.BookMyShow.dtos;

public class CityDTO {

    private int id;
    private String name;

    public CityDTO(int id, String name) {
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
