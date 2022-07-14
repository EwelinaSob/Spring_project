package com.example.ProjectUsers;

public class FieldNotEmptyException extends RuntimeException {
    public FieldNotEmptyException() {
     super("Incorrect data");
}
}
