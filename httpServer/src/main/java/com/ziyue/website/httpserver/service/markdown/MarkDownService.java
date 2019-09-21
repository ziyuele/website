/*
 * Copyright (c) 2019. website http://ziyuele.com
 * any questions you can  send mail 2429362606@qq.com
 */

package com.ziyue.website.httpserver.service.markdown;

import org.springframework.stereotype.Service;

import com.ziyue.website.httpserver.dao.beans.MarkDown;

@Service
public interface MarkDownService {

    public void addMarkDown(MarkDown markDown) throws Exception;

    public MarkDown getMarkDown(String titl);

}
