package com.semi.gamespace.main.controller;

import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping(value={"/", "/main"})
    public String main() {
        return "main/index";
    }

    @GetMapping(value={"/common/error/denied"})
    public void accessDenied() {}


    @PostMapping(value="/")
    public String redirectMain() {return "redirect:/";}
}
