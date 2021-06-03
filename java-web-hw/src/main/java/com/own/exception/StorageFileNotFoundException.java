package com.own.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StorageFileNotFoundException extends Exception {
    public StorageFileNotFoundException(String message) {
        super(message);
    }
}
