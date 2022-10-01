package com.ssafy.mas.database.repository;

import com.ssafy.mas.database.entity.Bookmark;
import com.ssafy.mas.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    public List<Bookmark> findBookmarksByMember(Member member);
}
