package site.mtcoding.bank.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.mtcoding.bank.domain.AuditingTime;
import site.mtcoding.bank.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 내가 new할 상황은 없다. JPA(hibernate)가 create 하면서 사용할 것.
@Getter
@Table(name = "account") // 테이블 생성 시 전부 다 따로 적어주자
@Entity
public class Account extends AuditingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private Long number;

    @Column(nullable = false, length = 50)
    private String ownerName; // 계좌주 시명

    @Column(nullable = false, length = 4) // 카카오페이 등은 6
    private String password;

    @Column(nullable = false) // 잔액은 많이 남으니까 Long으로 해야됨
    private Long balance; // 잔액

    // 커멜케이스는 DB에 언더스코어로 생성된다.
    @Column(nullable = false)
    private Boolean isActive; // 계좌 활성화 여부(true, false만 있어서 boolean으로 하면 됨)

    // lazyLoding으로 제어권을 내가 가져야 한다.
    // 연관관계에 있는 것들은 전부 Lazy
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // // 테이블 생성과 관련없는 양방향 매핑(화면 설계상)
    // @OneToMany(fetch = FetchType.LAZY)
    // private Transaction transaction;

    @Builder
    public Account(Long id, Long number, String ownerName, String password, Long balance, Boolean isActive, User user) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.password = password;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }

}
