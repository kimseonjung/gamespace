package com.semi.gamespace.support.controller;

import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import com.semi.gamespace.support.model.dto.SupportDTO;
import com.semi.gamespace.support.model.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = {"/support"})
public class SupportController {

    private final SupportService supportService;

    private final MemberService memberService;

    @Autowired
    public SupportController(SupportService supportService, MemberService memberService) {
        this.supportService = supportService;
        this.memberService = memberService;
    }

    @GetMapping("/supportList")
    public ModelAndView supportList(ModelAndView mv, Principal principal){
        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
        SupportDTO supportDTO = new SupportDTO();

        supportDTO.setMemberCode(memberInfo.getMemberCode());
        supportDTO.setMemberNickname(memberInfo.getUserNickname());

        List<SupportDTO> supportList = supportService.getList();
        supportList.stream().forEach(support -> System.out.println("support =" + support));



        mv.addObject("supportList", supportList);
        mv.setViewName("support/supportList");

        return mv;
    }

    @GetMapping("/view")
    public ModelAndView supportView(ModelAndView mv, String supportCode){

        SupportDTO supportView = supportService.getBoard(supportCode);

        mv.addObject("halo", supportView);



        mv.setViewName("support/view");

        return mv;
    }

    @GetMapping("/upload")
    public ModelAndView uploadBoardForm(ModelAndView mv){

        mv.setViewName("/support/upload");
        return mv;
    }


    @PostMapping("/upload")
    public ModelAndView uploadBoard(ModelAndView mv, SupportDTO supportDTO, Principal principal){
        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
        supportDTO.setMemberCode(memberInfo.getMemberCode());
        supportDTO.setMemberNickname(memberInfo.getUserNickname());

        supportService.uploadBoard(supportDTO);

        memberInfo.getUserNickname();

        mv.setViewName("redirect:/support/supportList");


        return mv;

    }

    @GetMapping("/update")
    public ModelAndView updateBoardForm(ModelAndView mv, String supportCode){

        mv.addObject("update", supportService.getBoard(supportCode));

        mv.setViewName("/support/update");
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView updateBoard(ModelAndView mv, SupportDTO supportDTO){
        supportService.updateBoard(supportDTO);
        mv.setViewName("redirect:/support/supportList");

        return mv;
    }

    @GetMapping("/delete")
    public ModelAndView deleteBoard(ModelAndView mv, String supportCode){
        supportService.deleteBoard(supportCode);

        mv.setViewName("redirect:/support/supportList");

        return mv;
    }


}
