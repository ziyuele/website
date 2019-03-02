/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ziyue.website.master.database.dao.Documents;

@Component
public interface DocumentsRepo extends JpaRepository<Documents, Long> {

    // add update
    @Override
    <S extends Documents> S save(S s);

    List<Documents> getByAuthor();

    Documents getById();

    Documents getByTitle();

    void deleteById();

    void deleteByAuthor();

    @Override
    void deleteAll();
}
