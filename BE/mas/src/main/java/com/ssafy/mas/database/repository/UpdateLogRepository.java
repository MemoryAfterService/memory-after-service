package com.ssafy.mas.database.repository;

import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.entity.UpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface UpdateLogRepository extends JpaRepository<UpdateLog, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UpdateLog as l " +
            "SET l.count = l.count + 1, l.lastUploadDate = :date " +
            "WHERE l.member = :member")
    public int updateProfileUploadLog(@Param("member") Member member,
                                      @Param("date") LocalDate date);
}
