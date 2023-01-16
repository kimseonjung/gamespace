package com.semi.gamespace.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/admin", "/"})
public class AdminController {

    @GetMapping("/adminMember")
    public ModelAndView adminPage(ModelAndView mv){
        mv.setViewName("admin/member");

        return mv;
    }


}
