package com.ziyue.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerContoler {

    @RequestMapping("/")
    public String test0(){
        return "index";
    }

    @RequestMapping("/index.html")
    public String test(){
        return "index";
    }


    @RequestMapping("/about.html")
    public String test1(){
        return "about";
    }

    @RequestMapping("/blog-listing.html")
    public String test2(){
        return "blog-listing";
    }

    @RequestMapping("/contact-us.html")
    public String test3(){
        return "contact-us";
    }

    @RequestMapping("/faq.html")
    public String test4(){
        return "faq";
    }

}
