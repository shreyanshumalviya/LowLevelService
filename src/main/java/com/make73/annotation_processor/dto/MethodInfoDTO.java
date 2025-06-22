package com.make73.annotation_processor.dto;

import java.io.Serializable;
import java.util.List;

public class MethodInfoDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;
    private String returnType;
    private String link;
    private String description;
    private List<ParameterInfoDTO> parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<ParameterInfoDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterInfoDTO> parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
