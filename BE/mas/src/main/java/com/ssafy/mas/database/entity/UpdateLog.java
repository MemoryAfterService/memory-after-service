package com.ssafy.mas.database.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "update_log")
@Entity
@Getter
@Setter
@ToString(exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLog extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private Member member;

    @Column(name = "count", nullable = false)
    @ColumnDefault("0")
    int count;

    @Column(name = "last_upload", nullable = true)
    LocalDate lastUploadDate;
}
