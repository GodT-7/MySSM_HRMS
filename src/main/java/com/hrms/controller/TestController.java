package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @auther thk
 * @date 2020/10/22 - 10:38
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(){
        System.out.println("hello");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("h","hello");
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
