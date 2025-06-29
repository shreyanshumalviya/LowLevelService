package com.make73.annotation_processor.lld_drivers.BookMyShow.dtos;

public class UserDTO {
    private int id;
    private String name;

    // constructor
    public UserDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
