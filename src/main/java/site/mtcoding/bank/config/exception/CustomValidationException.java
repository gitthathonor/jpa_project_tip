package site.mtcoding.bank.config.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomValidationException extends RuntimeException { // 무조건 400(BAD_REQUEST가 나옴)

    private final HttpStatus httpStatus;
    private Map<String, String> errorMap;

    // msg = "유효성 검사 실패"
    public CustomValidationException(Map<String, String> errorMap) {
        super("유효성 검사 실패");
        this.errorMap = errorMap;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

}
