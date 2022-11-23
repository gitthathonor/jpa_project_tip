package site.mtcoding.bank.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // 자식이 이 친구를 상속할건데, 자식이 이 친구를 테이블의 컬럼으로 만들어라.
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingTime {

    @LastModifiedDate // insert, update 시에 현재시간 들어감
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @CreatedDate // insert시에 현재시간
    @Column(nullable = false)
    protected LocalDateTime createdAt;

}
