package com.ssafy.mas.database.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "member")
@Entity
@Getter
@Setter
@ToString(exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Column(name = "user_id", length = 16, nullable = false)
    String userId;

    @Column(name = "password", length = 100, nullable = false)
    String password;

    @Column(name = "name", length = 30, nullable = false)
    String name;

    @Column(name = "phone", length = 100, nullable = false)
    String phone;

    @Column(name = "profile_url", length = 100, nullable = true)
    String profileUrl;

    @Column(name = "reg_date", nullable = true)
    LocalDateTime regDate;

    @Column(name = "withdrawal")
    @ColumnDefault("false")
    boolean withdrawal;

    @OneToOne(mappedBy = "member")
    private UpdateLog updateLog;
}


