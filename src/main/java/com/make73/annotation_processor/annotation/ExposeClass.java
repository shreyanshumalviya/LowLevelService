package com.make73.annotation_processor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ExposeClass {
    // ToDo handle space in link, non unique links
    // Required parameters
    String name();
    String link();
    String year() default "";
    String stack();
    String description();

}
