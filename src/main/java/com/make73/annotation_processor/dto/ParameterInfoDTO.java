package com.make73.annotation_processor.dto;

import java.io.Serializable;

public class ParameterInfoDTO implements Serializable {
    private static final long serialVersionUID = 3L;

    private String name;
    private String key;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
