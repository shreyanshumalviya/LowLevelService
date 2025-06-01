package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.ExposeClass;
import com.make73.annotation_processor.ExposeMethod;

@ExposeClass
public class Main {

    private String name;

    @ExposeMethod
    public String getName() {
        return "Hello world!";
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