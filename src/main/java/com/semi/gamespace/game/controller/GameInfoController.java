package com.semi.gamespace.game.controller;

import com.semi.gamespace.game.model.dto.*;
import com.semi.gamespace.game.model.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
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
    public ModelAndView selectGameMainList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json; charset=UTF-8");

        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();
        List<CategoryDTO> categoryList = gameInfoService.selectAllCategory();

        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()) {
            String name = (String) params.nextElement();
            System.out.print(name + " : " + request.getParameter(name) + "     ");
        }
        System.out.println();
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        List<CategoryDTO> categoryList2 = gameInfoService.selectOneCategory(request.getParameter("categoryNameBtn"));

        List<TagDTO> tagList = gameInfoService.selectAllTag();
        List<DevicesDTO> devicesList = gameInfoService.selectAllDevices();

        gameInfoList.stream().forEach(game ->System.out.println("game =" + game));
        categoryList.stream().forEach(category ->System.out.println("category = " + category));
        tagList.stream().forEach(tag -> System.out.println("tag = " + tag));
        devicesList.stream().forEach(devices ->System.out.println("devices = " + devices));

        mv.addObject("gameInfoList", gameInfoList);
        mv.addObject("categoryList", categoryList);
        mv.addObject("tagList", tagList);
        mv.addObject("devicesList", devicesList);
        mv.addObject("categoryList2", categoryList2);
        mv.setViewName("game/gameMain");

        return mv;

    }


    @GetMapping("/gameInfoDetail")
    public ModelAndView selectAllSystemList(ModelAndView mv){
        MinimumSystemDTO minimumSystem = gameInfoService.selectAllMinimumSystem();
        RecommendedSystemDTO recommendedSystem = gameInfoService.selectAllRecommendedSystem();
        System.out.println("mini =" + minimumSystem);
        System.out.println("rec =" + recommendedSystem);

        mv.addObject("minimumSystem", minimumSystem);
        mv.addObject("recommendedSystem", recommendedSystem);
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
