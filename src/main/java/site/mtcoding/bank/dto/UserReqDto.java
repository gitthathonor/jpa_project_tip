package site.mtcoding.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import site.mtcoding.bank.config.enums.UserEnum;
import site.mtcoding.bank.domain.user.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto {
        @Size(min = 2, max = 20)
        @NotBlank(message = "유저네임은 필수입니다.")
        private String username;

        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문, 숫자, 최소 4자리에서 최대 20까지입니다.")
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(UserEnum.CUSTOMER)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class LoginReqDto {
        @Size(min = 2, max = 20)
        @NotBlank(message = "유저네임은 필수입니다.")
        private String username;

        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문, 숫자, 최소 4자리에서 최대 20까지입니다.")
        private String password;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .role(UserEnum.CUSTOMER)
                    .build();
        }
    }

}
