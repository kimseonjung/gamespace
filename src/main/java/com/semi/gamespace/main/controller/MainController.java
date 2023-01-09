package com.semi.gamespace.main.controller;

import com.semi.gamespace.authentication.model.dto.SpaceUser;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    @GetMapping(value={"/", "/main"})
    public String main() {
        return "/main/index";
    }

    @GetMapping(value={"/common/error/denied"})
    public void accessDenied() {}


    @PostMapping(value="/")
    public String mainPost() {
        return "redirect:/";
    }
}
