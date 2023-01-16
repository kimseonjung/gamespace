package com.semi.gamespace.support.controller;

import com.semi.gamespace.support.model.dto.SupportDTO;
import com.semi.gamespace.support.model.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/support"})
public class SupportController {

    private final SupportService supportService;

    @Autowired
    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @GetMapping("/supportList")
    public ModelAndView supportList(ModelAndView mv){

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



        mv.setViewName("freeGal/view");

        return mv;
    }


}
