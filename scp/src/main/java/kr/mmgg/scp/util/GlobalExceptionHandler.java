package kr.mmgg.scp.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        switch (e.getErrorCode().getHttpStatus().value()) {
            case 404:
                return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
