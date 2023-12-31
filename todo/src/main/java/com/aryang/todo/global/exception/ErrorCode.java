package com.aryang.todo.global.exception;

public enum ErrorCode {
    INVALID_REQUEST(400, "Invalid request"),
    INVALID_VALUE_TYPES(400, "Invalid value types"),
    UNAUTHORIZED_ACCESS(403, "No authorization"),
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    METHOD_NOT_ALLOWED(405,  "Invalid Method"),
    UNSUPPORTED_MEDIA_TYPE(415, "Media type not supported"),
    PAYLOAD_TOO_LARGE(413, "File size exceeds maximum limit"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),

    ALREADY_EXISTS_EMAIL(409, "Email is not available"),
    ALREADY_EXISTS_NICKNAME(409, "Nickname is not available"),

    TODO_NOT_FOUND(404, "Todo not found");



    private final int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
