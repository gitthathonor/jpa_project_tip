package site.mtcoding.bank.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import site.mtcoding.bank.config.enums.UserEnum;
import site.mtcoding.bank.domain.AudingTime;

@Getter
@Table(name = "users") // 테이블 생성 시 전부 다 따로 적어주자
@Entity
public class User extends AuditingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String useranme;
    private String password;
    private String emaill;
    private UserEnum role; // ADMIN, CUSTOMER

}
