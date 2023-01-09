package com.semi.gamespace.game.controller;

import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.dto.MinimumSystemDTO;
import com.semi.gamespace.game.model.dto.RecommendedSystemDTO;
import com.semi.gamespace.game.model.dto.SpecificationDTO;
import com.semi.gamespace.game.model.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = {"/game","/"})
public class GameInfoController {

    private final GameInfoService gameInfoService;

    private  final MessageSource messageSource;

    @Autowired
    public GameInfoController(GameInfoService gameInfoService, MessageSource messageSource) {
        this.gameInfoService = gameInfoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/game")
    public ModelAndView selectGameInfoList(ModelAndView mv){
        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();
        gameInfoList.stream().forEach(game ->System.out.println("game =" + game));

        mv.addObject("gameInfoList", gameInfoList);
        mv.setViewName("game/gameMain");

        return mv;

    }

    @GetMapping("/gameInfoDetail")
    public ModelAndView selectAllSystemList(ModelAndView mv){
        List<MinimumSystemDTO> minimumSystemList = gameInfoService.selectAllMinimumSystem();
        List<RecommendedSystemDTO> recommendedSystemList = gameInfoService.selectAllRecommendedSystem();
        minimumSystemList.stream().forEach(mini ->System.out.println("mini =" + mini));
        recommendedSystemList.stream().forEach(rec ->System.out.println("rec =" + rec));

        mv.addObject("minimumSystemList", minimumSystemList);
        mv.addObject("recommendedSystemList", recommendedSystemList);
        mv.setViewName("game/gameInfoDetail");

        return mv;

    }



    @GetMapping("gameInfoInsert")
    public void registPage(){}




    @PostMapping("gameInfoInsert")
    public ModelAndView registGameInfo(ModelAndView mv, GameInfoDTO newGameInfo, SpecificationDTO newSpecification, MinimumSystemDTO newMinimumSystem, RecommendedSystemDTO newRecommendedSystem , RedirectAttributes rttr, Locale locale) throws Exception{
        gameInfoService.registGameInfo(newGameInfo);

        gameInfoService.registMinimumSystem(newMinimumSystem);
        gameInfoService.registRecommendedSystem(newRecommendedSystem);
        gameInfoService.registSpecification(newSpecification);

        mv.setViewName("redirect:/game/game");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registGameInfo", null, locale));


        return mv;
    }




}
