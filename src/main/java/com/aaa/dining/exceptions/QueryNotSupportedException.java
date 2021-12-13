package com.aaa.dining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class QueryNotSupportedException extends Exception {
    public QueryNotSupportedException(String message) {
        super(message);
    }
}
