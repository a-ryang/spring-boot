package com.aryang.todo.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(final ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode errorCode, final List<FieldError> errors) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
        return new ErrorResponse(errorCode, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final ConstraintViolationException e) {
        final List<ErrorResponse.FieldError> errors = e.getConstraintViolations().stream()
                .map(violation -> {
                    String fullPath = violation.getPropertyPath().toString();
                    String fieldName = fullPath.substring(fullPath.lastIndexOf('.') + 1); // 마지막 '.' 이후의 문자열을 필드 이름으로 추출
                    return new ErrorResponse.FieldError(
                            fieldName,
                            violation.getInvalidValue() == null ? "" : violation.getInvalidValue().toString(),
                            violation.getMessage());
                })
                .collect(Collectors.toList());

        return new ErrorResponse(ErrorCode.INVALID_REQUEST, errors);
    }


    public static ErrorResponse of(final MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_VALUE_TYPES, errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
