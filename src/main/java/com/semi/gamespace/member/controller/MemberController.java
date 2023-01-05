package com.semi.gamespace.member.controller;

import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public void memberLoginForm() {}

    @GetMapping("/regist")
    public void memberRegistForm() {}

    @GetMapping("/denied")
    public String memberAccessDenied() { return "/common/error/denied"; }

    @GetMapping("/insert/success")
    public void memberRegistSuccess() {}

    @GetMapping("/insert/failure")
    public void memberRegistFailure() {}

    @PostMapping("/regist")
    public ModelAndView registMember(ModelAndView mv, MemberDTO newMember) throws Exception {
        String result = "";
        if(memberService.registMember(newMember)) {
            result = "success";
        } else {
            result = "failrue";
        }
        mv.setViewName("redirect:/member/insert/"+result);

        return mv;
    }
}
