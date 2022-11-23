package site.mtcoding.bank.domain.transaction;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.mtcoding.bank.config.enums.TrasactionEnum;
import site.mtcoding.bank.domain.AuditingTime;
import site.mtcoding.bank.domain.account.Account;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 내가 new할 상황은 없다. JPA(hibernate)가 create 하면서 사용할 것.
@Getter
@Table(name = "transaction") // 테이블 생성 시 전부 다 따로 적어주자
@Entity
public class Transaction extends AuditingTime { // 이제 이력을 남기는 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount; // 출금계좌

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; // 입금 계좌

    @Column(nullable = false)
    private Long amount; // 금액

    private Long withdrawAccountBalance; // 거래 후 잔액
    private Long depositAccountBalance; // 거래 후 잔액

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TrasactionEnum gubun; // null값으로 구분을 하는 것보다는 구분 데이터를 하나 주는 것이 좋다. 입금(ATM으로부터), 출금(ATM으로), 이체(다른계좌로)
    // 각각의 검증들은 service에서 로직처리를 해야한다.
    // 쓸데없이 DB에 접근할 이유가 없다. 서비스에서 전부 다 잡고 하면 된다.

    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount,
            Long withdrawAccountBalance, Long depositAccountBalance, TrasactionEnum gubun) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
    }

}
