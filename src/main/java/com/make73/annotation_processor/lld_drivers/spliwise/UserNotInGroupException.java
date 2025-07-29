package com.make73.annotation_processor.lld_drivers.spliwise;

public class UserNotInGroupException extends Exception {
    UserNotInGroupException() {
        super("User not in group");
    }
}
