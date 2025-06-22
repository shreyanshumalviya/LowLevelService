package com.make73.annotation_processor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ExposeMethod {
    // ToDo handle space in link, non unique links
    String name();
    String link();
    String description();
}
