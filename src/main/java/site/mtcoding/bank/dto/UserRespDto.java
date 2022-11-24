package site.mtcoding.bank.dto;

import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import site.mtcoding.bank.domain.user.User;

public class UserRespDto {
    // DTO의 역할 => 화면에 그리게 하던지, 프론트에서 작업을 진행한다던지(alert 창에 띄우는 듯이)
    // 잘 모르겠으면 일단 다 던진 다음에 추후에 빼면 된다.

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long id; // 99.9프로 id가 필요하다. 프론트에서 제어를 위해서 필요하다.
        private String username;

        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }

    }

    @Setter
    @Getter
    public static class LoginRespDto {
        private Long id;
        private String username;
        private String createdAt;

        public LoginRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.createdAt = user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

    }
}
