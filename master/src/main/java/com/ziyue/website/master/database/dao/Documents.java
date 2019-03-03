/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */


package com.ziyue.website.master.database.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  this is document bean used to save user articles
 */

@ToString
@Setter
@Getter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "website_documents")
public class Documents implements Serializable {

    // https://www.cnblogs.com/pcheng/p/7060983.html
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "last_update_time")
    private String lastUpdateTime;

    @Column(name = "documents")
    private String documents;

    @Column(name = "author")
    private String author;

}
