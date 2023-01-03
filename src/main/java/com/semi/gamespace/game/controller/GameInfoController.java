package com.semi.gamespace.game.controller;

import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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



}
