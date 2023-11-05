package com.aryang.todo.global.exception;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class AppExceptionHandler {

    /**
     * - 주로 @Valid 또는 @Validated에서 발생한 요청 데이터 검증 실패 예외를 처리
     * - HttpMessageConverter에서 등록한 HttpMessageConverter binding 못 할경우 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_REQUEST, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * - Validation API에서 제약 조건 위반이 발생할 때 발생하는 예외를 처리
     * - @Min(1)인데 1 이하 값  등
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * - 메서드 파라미터의 타입이 잘못되었을 때 발생하는 예외를 처리
     * - 주로 URL 경로 변수의 타입이 예상된 타입과 일치하지 않을 때 발생
     * - {id} id가 Long인데 String abc 값 등
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * - HTTP 요청 메소드가 지원되지 않을 때 발생하는 예외를 처리
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * - 요청 본문을 파싱할 수 없는 예외를 처리
     * - JSON 형식이 잘못된 경우 이 예외가 발생할 수 있음
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_REQUEST.getStatus()));
    }

    /**
     * - 클라이언트가 요청에서 지원하지 않는 미디어 타입을 사용했을 때 예외 처리
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.UNSUPPORTED_MEDIA_TYPE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getStatus()));
    }

    /**
     * - 파일 업로드 시 허용된 최대 크기를 초과했을 때 예외 처리
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.PAYLOAD_TOO_LARGE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.PAYLOAD_TOO_LARGE.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        System.out.println(e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
