package kr.mmgg.scp.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        if (e.getErrorCode().getHttpStatus().value() >= 500) {
            ErrorResponse.toResponseEntity(e.getErrorCode());
        }
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
