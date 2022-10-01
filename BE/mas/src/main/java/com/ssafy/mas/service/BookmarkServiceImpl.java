package com.ssafy.mas.service;

import com.ssafy.mas.database.entity.Bookmark;
import com.ssafy.mas.database.entity.Member;
import com.ssafy.mas.database.repository.BookmarkRepository;
import com.ssafy.mas.database.repository.MemberRepository;
import com.ssafy.mas.request.BookmarkReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service("bookmarkService")
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Override
    public List<String> saveNumbers(BookmarkReq bookmarkReq) {
        String userId = bookmarkReq.getUserid();
        List<String> tels = bookmarkReq.getTelset();

        Member findMember = memberRepository.findFirstByUserIdAndWithdrawal(userId, false);

        List<Bookmark> oldBookmarks = bookmarkRepository.findBookmarksByMember(findMember);
        bookmarkRepository.deleteAllInBatch(oldBookmarks);

        List<Bookmark> newBookmarks = new ArrayList<>();
        for (int i = 0; i < tels.size(); i++) {
            newBookmarks.add(new Bookmark(findMember, tels.get(i)));
        }

        if(bookmarkRepository.saveAll(newBookmarks).size() == tels.size()) {
            List<String> result = new ArrayList<>();

            for (int i = 0; i < tels.size(); i++) {
                result.add(tels.get(i));
            }

            return result;
        }

        return null;
    }
}
