package com.ssafy.mas.service;

import com.ssafy.mas.request.BookmarkReq;

import java.util.List;

public interface BookmarkService {
    public List<String> saveNumbers(BookmarkReq bookmarkReq);
}
