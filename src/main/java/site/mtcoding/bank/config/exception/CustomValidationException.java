package site.mtcoding.bank.config.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationException extends RuntimeException { // 무조건 400(BAD_REQUEST가 나옴)

    private final int httpStatusCode = 400;
    private Map<String, String> errorMap;

    // msg = "유효성 검사 실패"
    public CustomValidationException(Map<String, String> errorMap) {
        super("유효성 검사 실패");
        this.errorMap = errorMap;
    }

}
