package com.semi.gamespace.free_gal.controller;

import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import com.semi.gamespace.free_gal.model.service.FreeGalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        mv.addObject("halo", freeGalView);

        mv.setViewName("freeGal/view");

        return mv;
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


}
