package site.mtcoding.bank.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.mtcoding.bank.config.exception.CustomApiException;
import site.mtcoding.bank.dto.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        log.debug("디버그 : CustomExceptionHandler 실행됨");
        // body에는 공통DTO를 하나 만들어서 넣어주는 것이 좋다.
        return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), null), e.getHttpStatus());

    }
}
