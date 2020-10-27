package com.test.web.rest.errors;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadRequestAlertException extends RuntimeException {

    private String message;
    private String entityName;
    private String idexists;

    public BadRequestAlertException(String message, String entityName, String idexists) {
        super(message);
        this.message = message;
        this.entityName = entityName;
        this.idexists = idexists;
    }
}
