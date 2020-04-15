package com.oocl.exception;

public class UserInputIncorrectException extends Exception {
    public static final String USER_INPUT_ERROR = "User input should between 0-9!";

    public UserInputIncorrectException(String message) {
        super(message);
    }
}
