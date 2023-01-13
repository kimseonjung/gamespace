package com.semi.gamespace.free_gal.controller;

import com.semi.gamespace.free_gal.model.dto.FreeGalComDTO;
import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import com.semi.gamespace.free_gal.model.service.FreeGalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = {"/freeGal"})
public class FreeGalController {
    private final FreeGalService freeGalService;

    @Autowired
    public FreeGalController(FreeGalService freeGalService) {
        this.freeGalService = freeGalService;
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

    @GetMapping("/view")
    public ModelAndView freeGalView(ModelAndView mv, String freeGalCode){

        FreeGalDTO freeGalView = freeGalService.getBoard(freeGalCode);

        //댓글 데이터를 freeGalComList에 담기
        mv.addObject("freeGalComList", freeGalService.getComment(freeGalCode));
        // 대댓글 데이터를 replyReplyList 모델 객체에 담기
        mv.addObject("freeGalComComList", freeGalService.getCommentComment(freeGalCode));

        mv.addObject("halo", freeGalView);



        mv.setViewName("freeGal/view");
        System.out.println("111111111111111111111111111111111111111111111111111");
        return mv;
    }

    @PostMapping("/view")
    public String uploadComment(String freeGalCode, FreeGalComDTO freeGalComDTO) {
        freeGalService.uploadComment(freeGalComDTO);

        return "redirect:/freeGal/view?freeGalCode="+freeGalCode;


    }

    @GetMapping("/upload")
    public ModelAndView uploadBoardForm(ModelAndView mv){

        mv.setViewName("/freeGal/upload");
        return mv;
    }

    @PostMapping("/upload")
    public ModelAndView uploadBoard(ModelAndView mv, FreeGalDTO freeGalDTO){
        freeGalService.uploadBoard(freeGalDTO);

        mv.setViewName("redirect:/freeGal/freeGalList");


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
    public ModelAndView uploadCommentComment(ModelAndView mv, String freeGalCode, FreeGalComDTO freeGalComDTO) {
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
