/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.fileserver.database.dao;

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

@ToString
@Setter
@Getter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "website_datasource")
public class DataSource {
    // https://www.cnblogs.com/pcheng/p/7060983.html
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_path")
    private String path;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "last_update_time")
    private String lastUpdateTime;
}
