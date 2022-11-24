package site.mtcoding.bank.temp.config.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import site.mtcoding.bank.config.exception.CustomApiException;

public class CustomApiExceptionTest {

    @Test
    public void _test() throws Exception {
        // given
        String msg = "해당 id가 없습니다.";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // when
        CustomApiException ex = new CustomApiException("해당 id가 없습니다.", httpStatus);
        System.out.println(ex.getHttpStatus());
        System.out.println(ex.getHttpStatus().value());
        System.out.println(ex.getMessage());

        // then
        assertThat(ex.getHttpStatus().value()).isEqualTo(400);
        assertThat(ex.getMessage()).isEqualTo("해당 id가 없습니다.");

    }
}
