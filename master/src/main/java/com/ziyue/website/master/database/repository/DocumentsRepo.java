/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.master.database.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ziyue.website.master.database.dao.Documents;

@Component
@Transactional
public interface DocumentsRepo extends JpaRepository<Documents, Long> {

    // add update
    @Override
    <S extends Documents> S save(S s);

    List<Documents> getAllByAuthor(String author);

    Documents getById(long id);

    Documents getByTitle(String title);

    void deleteById(long id);

    void deleteAllByAuthor(String author);

    @Override
    void deleteAll();
}
