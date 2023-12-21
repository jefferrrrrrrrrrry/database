package com.example.buaadb.exception;

import com.example.buaadb.common.Status;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private Status code;

    public ServiceException(Status code, String msg) {
        super(msg);
        this.code = code;
    }
}
