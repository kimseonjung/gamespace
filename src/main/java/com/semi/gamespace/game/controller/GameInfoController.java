package com.semi.gamespace.game.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.gamespace.game.model.dto.*;
import com.semi.gamespace.game.model.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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



    @GetMapping("game")
    public ModelAndView selectGameMainList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){

        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();
        List<CategoryDTO> categoryList = gameInfoService.selectAllCategory();
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
        mv.setViewName("game/gameMain");

        return mv;

    }

    @PostMapping("game")
    public ModelAndView selectGameMainJsonList( HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("jsonView");
        response.setContentType("application/json; charset=UTF-8");

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();



        List<CategoryDTO> categoryList = gameInfoService.selectOneCategory(request.getParameter("categoryCode"));

        mv.addObject("categoryList", gson.toJson(categoryList));
        //mv.setViewName("jsonView");

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
