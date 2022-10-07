package com.ssafy.mas.database.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@Getter
@Setter
@ToString(exclude = {"bookmarks", "updateLog"})
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

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Bookmark> bookmarks = new ArrayList<>();
}


