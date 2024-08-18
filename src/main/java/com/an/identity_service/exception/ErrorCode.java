package com.an.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error"),
    INVALID_KEY(1001, "Invalid Message Key"),
    USER_EXISTED(1002, "User Existed"),
    USERNAME_INVALID(1003, "Username must be at least 4 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters"),;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
