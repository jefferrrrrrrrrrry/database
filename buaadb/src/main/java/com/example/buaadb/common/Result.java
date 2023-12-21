package com.example.buaadb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.buaadb.common.Status.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Status status;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(SUCCESS, "", null);
    }

    public static Result success(Object data) {
        return new Result(SUCCESS, "", data);
    }

    public static Result error(String msg) {
        return new Result(UNKNOWN_ERROR, msg, null);
    }
    public static Result error(Status status, String msg) {
        return new Result(status, msg, null);
    }
}
