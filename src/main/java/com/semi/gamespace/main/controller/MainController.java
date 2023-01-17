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

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    private final MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value={"/", "/main"})
    public ModelAndView main(ModelAndView mv, Principal principal) {
        if(principal != null) {
            MemberDTO currUser = memberService.findMemberById(principal.getName());
            if(currUser.getMemberStatus().equals("N")) {
                Date now = new Date(System.currentTimeMillis());
                Date banned = currUser.getBanDate();
                if(now.before(banned)) {
                    mv.addObject("banDuration", currUser.getBanDate());
                    mv.setViewName("/common/error/banned");
                    return mv;
                } else {
                    memberService.memberUnbanByCode(currUser.getMemberCode());
                }
            }
        }
        mv.setViewName("/main/index");
        return mv;
    }

    @GetMapping(value={"/common/error/denied"})
    public void accessDenied() {}


    @PostMapping(value="/")
    public String mainPost() {
        return "redirect:/";
    }


}
