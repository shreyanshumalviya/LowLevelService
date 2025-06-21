package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;

@ExposeClass
public class Main {

    private String name;

    @ExposeMethod
    public String getName() {
        return "Hello " + name + "!";
    }

    @ExposeMethod
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    @ExposeMethod
    public void setName2() {
    }


}