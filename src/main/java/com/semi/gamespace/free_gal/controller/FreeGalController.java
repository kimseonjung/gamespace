package com.semi.gamespace.free_gal.controller;

import com.semi.gamespace.free_gal.model.dto.FreeGalComDTO;
import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import com.semi.gamespace.free_gal.model.service.FreeGalService;
import com.semi.gamespace.member.model.dto.MemberDTO;
import com.semi.gamespace.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = {"/freeGal"})
public class FreeGalController {
    private final FreeGalService freeGalService;
    private final MemberService memberService;

    @Autowired
    public FreeGalController(FreeGalService freeGalService, MemberService memberService) {
        this.freeGalService = freeGalService;
        this.memberService = memberService;
    }

    @GetMapping("/freeGalList")
    public ModelAndView freeGalList(ModelAndView mv){


        List<FreeGalDTO> freeGalListNotice = freeGalService.getListNotice();
        List<FreeGalDTO> freeGalList = freeGalService.getList();
        freeGalList.stream().forEach(freeGal -> System.out.println("freeGal =" + freeGal));
        freeGalListNotice.stream().forEach(freeGalNotice -> System.out.println("freeGal =" + freeGalNotice));

        mv.addObject("freeGalListNotice", freeGalListNotice);
        mv.addObject("freeGalList", freeGalList);
        mv.setViewName("freeGal/freeGalList");

        return mv;
    }

    @GetMapping("/freeGalListNotice")
    public ModelAndView freeGalListNotice(ModelAndView mv){
        List<FreeGalDTO> freeGalListNotice = freeGalService.getListNotice();

        freeGalListNotice.stream().forEach(freeGalNotice -> System.out.println("freeGal =" + freeGalNotice));

        mv.addObject("freeGalListNotice", freeGalListNotice);

        mv.setViewName("freeGal/freeGalListNotice");

        return mv;
    }

    @GetMapping("/view")
    public ModelAndView freeGalView(ModelAndView mv, String freeGalCode){

        FreeGalDTO freeGalView = freeGalService.getBoard(freeGalCode);

        //댓글 데이터를 freeGalComList에 담기
        mv.addObject("freeGalComList", freeGalService.getComment(freeGalCode));
        // 대댓글 데이터를 replyReplyList 모델 객체에 담기
        mv.addObject("freeGalComComList", freeGalService.getCommentComment(freeGalCode));

        mv.addObject("halo", freeGalView);



        mv.setViewName("freeGal/view");

        return mv;
    }

    @PostMapping("/view")
    public String uploadComment(String freeGalCode, FreeGalComDTO freeGalComDTO, Principal principal) {
        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
        freeGalComDTO.setMemberCode(memberInfo.getMemberCode());
        freeGalComDTO.setMemberNickname(memberInfo.getUserNickname());

        freeGalService.uploadComment(freeGalComDTO);

        return "redirect:/freeGal/view?freeGalCode="+freeGalCode;


    }

    @GetMapping("/upload")
    public ModelAndView uploadBoardForm(ModelAndView mv){

        mv.setViewName("/freeGal/upload");
        return mv;
    }

    @GetMapping("/uploadNotice")
    public ModelAndView uploadBoardNoticeForm(ModelAndView mv){

        mv.setViewName("freeGal/uploadNotice");

        return mv;
    }

    @PostMapping("/upload")
    public ModelAndView uploadBoard(ModelAndView mv, FreeGalDTO freeGalDTO, Principal principal){
        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
        freeGalDTO.setMemberCode(memberInfo.getMemberCode());
        freeGalDTO.setMemberNickname(memberInfo.getUserNickname());

        freeGalService.uploadBoard(freeGalDTO);

        memberInfo.getUserNickname();

        mv.setViewName("redirect:/freeGal/freeGalList");


        return mv;

    }

    @PostMapping("/uploadNotice")
    public ModelAndView uploadBoardNotice(ModelAndView mv, FreeGalDTO freeGalDTO){
        freeGalService.uploadBoardNotice(freeGalDTO);

        mv.setViewName("redirect:/freeGal/freeGalListNotice");


        return mv;

    }

    @GetMapping("/update")
    public ModelAndView updateBoardForm(ModelAndView mv, String freeGalCode){

        mv.addObject("update", freeGalService.getBoard(freeGalCode));

        mv.setViewName("/freeGal/update");
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView updateBoard(ModelAndView mv, FreeGalDTO freeGalDTO){
        freeGalService.updateBoard(freeGalDTO);
        mv.setViewName("redirect:/freeGal/freeGalList");

        return mv;
    }

    @GetMapping("/delete")
    public ModelAndView deleteBoard(ModelAndView mv, String freeGalCode){
        freeGalService.deleteBoard(freeGalCode);

        mv.setViewName("redirect:/freeGal/freeGalList");

        return mv;
    }

    @PostMapping("/updateComment")
    public ModelAndView updateComment(String freeGalCode, ModelAndView mv, FreeGalComDTO freeGalComDTO){
        freeGalService.updateComment(freeGalComDTO);

        mv.setViewName("redirect:/freeGal/view?freeGalCode="+freeGalCode);

        return mv;
    }

    @GetMapping("/deleteComment")
    public ModelAndView deleteComment(String freeGalCode, ModelAndView mv, String freeGalComCode) {
        freeGalService.deleteComment(freeGalComCode);

        mv.setViewName("redirect:/freeGal/view?freeGalCode="+freeGalCode);

        return mv;
    }

    @PostMapping("/uploadCommentComment")
    public ModelAndView uploadCommentComment(ModelAndView mv, String freeGalCode, FreeGalComDTO freeGalComDTO, Principal principal) {
        MemberDTO memberInfo = memberService.findMemberById(principal.getName());
        freeGalComDTO.setMemberCode(memberInfo.getMemberCode());
        freeGalComDTO.setMemberNickname(memberInfo.getUserNickname());

        freeGalService.uploadCommentComment(freeGalComDTO);

        mv.setViewName("redirect:/freeGal/view?freeGalCode="+freeGalCode);

        return mv;
    }

    @PostMapping("/updateCommentComment")
    public ModelAndView updateCommentComment(ModelAndView mv, String freeGalCode, FreeGalComDTO freeGalComDTO) {
        freeGalService.updateCommentComment(freeGalComDTO);

        mv.setViewName("redirect:/freeGal/view?freeGalCode="+freeGalCode);

        return mv;
    }

    @GetMapping("/deleteCommentComment")
    public ModelAndView deleteCommentComment(ModelAndView mv, String freeGalCode, String freeGalComCode) {
        freeGalService.deleteCommentComment(freeGalComCode);

        mv.setViewName("redirect:/freeGal/view?freeGalCode="+freeGalCode);

        return mv;
    }

}
