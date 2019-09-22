/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.dao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ziyue.website.httpserver.dao.beans.MarkDown;

@Component
@Transactional
public interface MarkDownRepo extends JpaRepository<MarkDown, Long> {

    @Override
    <S extends MarkDown> S save(S s);

    MarkDown getByTitle(String title);

    List<MarkDown> getTopByLastUpdateTime(int topNum);

}




