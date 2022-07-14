package com.example.ProjectUsers;

import org.hibernate.service.spi.ServiceException;

public class ConflictException extends ServiceException {

    public ConflictException() {
        super("User exists");
    }
}
