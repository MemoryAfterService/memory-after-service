package com.ssafy.mas.database.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "bookmark")
@Entity
@Getter
@Setter
@ToString(exclude = {"member"})
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private Member member;

    @Column(name = "sms_number", length = 20, nullable = false)
    private String smsNumber;
}
