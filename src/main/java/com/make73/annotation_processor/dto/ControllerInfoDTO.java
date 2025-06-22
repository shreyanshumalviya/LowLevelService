package com.make73.annotation_processor.dto;


import java.io.Serializable;
import java.util.List;

public class ControllerInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String year;
    private String stack;
    private String link;
    private String description;

    private List<MethodInfoDTO> methods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MethodInfoDTO> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfoDTO> methods) {
        this.methods = methods;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
