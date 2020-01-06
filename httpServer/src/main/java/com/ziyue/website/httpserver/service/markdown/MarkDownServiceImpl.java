/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.service.markdown;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import com.ziyue.website.common.Commons;
import com.ziyue.website.httpserver.dao.beans.MarkDown;
import com.ziyue.website.httpserver.dao.repository.MarkDownRepo;


@Service("markDownService")
public class MarkDownServiceImpl implements MarkDownService {

    private MarkDownRepo repo;

    @Autowired
    public MarkDownServiceImpl(MarkDownRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transient
    public void addMarkDown(MarkDown markDown) throws Exception {
        markDown.setLastUpdateTime(Commons.TIME_STAMP());
        repo.save(markDown);
    }

    @Override
    public MarkDown getMarkDown(String title) {
        return repo.getByTitle(title);
    }

    @Override
    public List<MarkDown> listMarkDown() {
        return repo.getAllByLastUpdateTime("10");
    }
}
