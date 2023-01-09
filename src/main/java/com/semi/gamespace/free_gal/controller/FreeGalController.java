package com.semi.gamespace.free_gal.controller;

import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import com.semi.gamespace.free_gal.model.service.FreeGalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/freeGal")
public class FreeGalController {
    private final FreeGalService freeGalService;

    @Autowired
    public FreeGalController(FreeGalService freeGalService) {
        this.freeGalService = freeGalService;
    }

    @GetMapping("/freeGalList")
    public ModelAndView freeGalList(ModelAndView mv){
        List<FreeGalDTO> freeGalList = freeGalService.getList();
        freeGalList.stream().forEach(freeGal -> System.out.println("freeGal =" + freeGal));

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

}
