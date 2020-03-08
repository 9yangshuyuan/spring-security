package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }



    @RequestMapping("/leve1/{id}")
    public String toLogin1(@PathVariable int id){
        return "/views/leve1/"+id;
    }

    @RequestMapping("/leve2/{id}")
    public String toLogin2(@PathVariable int id){
        return "/views/leve2/"+id;
    }

    @RequestMapping("/leve3/{id}")
    public String toLogin3(@PathVariable int id){
        return "/views/leve3/"+id;
    }
}
