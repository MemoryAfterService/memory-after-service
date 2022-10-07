package com.ssafy.mas.database.repository;

import com.ssafy.mas.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findFirstByUserId(String inputId);
    public Member findFirstByUserIdAndWithdrawal(String inputId, boolean isDeleted);
    public Member findByNameAndPhone(String name, String phone);

    @Modifying
    @Transactional
    @Query("UPDATE Member as m " +
            "SET m.password = :randomPwd " +
            "WHERE m.userId = :userId and m.name = :name and m.phone = :phone")
    public int updateRandomPassword(@Param("userId") String userId,
                              @Param("name") String name,
                              @Param("phone") String phone,
                              @Param("randomPwd") String randomPwd);
    @Modifying
    @Transactional
    @Query("UPDATE Member as m " +
            "SET m.profileUrl = :url " +
            "WHERE m.userId = :userId")
    public int updateProfileImageUrl(@Param("userId") String userId,
                                  @Param("url") String url);

    @Modifying
    @Transactional
    @Query("UPDATE Member as m " +
            "SET m.password = :password " +
            "WHERE m.userId = :userId")
    public int updatePassword(@Param("password") String inputPwd,
                              @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Member as m " +
            "SET m.withdrawal = true " +
            "WHERE m.userId = :userId")
    public int updateIsDeleted(@Param("userId") String userId);
}
