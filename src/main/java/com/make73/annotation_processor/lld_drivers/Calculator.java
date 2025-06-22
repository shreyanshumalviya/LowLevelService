package com.make73.annotation_processor.lld_drivers;

import com.make73.annotation_processor.annotation.ExposeClass;
import com.make73.annotation_processor.annotation.ExposeMethod;

@ExposeClass(name = "Calculator", link = "Calculator", year = "2023", stack = "Java", description = "A simple calculator project")
public class Calculator {

    @ExposeMethod(name = "Add", link = "add", description = "This method lets you add 2 numbers")
    public int add(int a, int b) {
        return a + b;
    }

    @ExposeMethod(name = "Subtract", link = "subtract", description = "This method lets you subtract 2 numbers")
    public int subtract(int a, int b) {
        return a - b;
    }
}
