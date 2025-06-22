package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;

@ExposeClass(name = "Main", link = "Main", year = "2024", stack = "Java", description = "Main class")
public class Main {

    private String name;

    @ExposeMethod(name = "Get Name", link = "getName", description = "Returns the name of the object")
    public String getName() {
        return "Hello " + name + "!";
    }

    @ExposeMethod(name = "Set Name", link = "setName", description = "Sets the name of the object")
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    @ExposeMethod(name = "Set Default Name", link = "setName2", description = "Sets the default \"World\" as name of the object")
    public void setName2() {
        this.name = "World";
    }


}