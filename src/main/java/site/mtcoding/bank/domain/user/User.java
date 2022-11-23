package site.mtcoding.bank.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.mtcoding.bank.config.enums.UserEnum;
import site.mtcoding.bank.domain.AuditingTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 내가 new할 상황은 없다. JPA(hibernate)가 create 하면서 사용할 것.
@Getter
@Table(name = "users") // 테이블 생성 시 전부 다 따로 적어주자
@Entity
public class User extends AuditingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String useranme;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(unique = true, nullable = false, length = 50)
    private String emaill;

    @Enumerated(EnumType.STRING) // Enum타입이 DB에 없기 때문에 타입을 지정해줘야 한다.
    @Column(unique = true, nullable = false)
    private UserEnum role; // ADMIN, CUSTOMER

    @Builder
    public User(Long id, String useranme, String password, String emaill, UserEnum role) {
        this.id = id;
        this.useranme = useranme;
        this.password = password;
        this.emaill = emaill;
        this.role = role;
    }

}
