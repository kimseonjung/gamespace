package com.semi.gamespace.news.controller;

import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.service.GameInfoService;
import com.semi.gamespace.news.model.dto.NewsDTO;
import com.semi.gamespace.news.model.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = {"/news", "/"})
public class NewsInfoController {

    private final NewsInfoService newsInfoService;
    private final GameInfoService gameInfoService;


    private  final MessageSource messageSource;

    @Autowired
    public NewsInfoController(NewsInfoService newsInfoService,GameInfoService gameInfoService, MessageSource messageSource) {
        this.newsInfoService = newsInfoService;
        this.gameInfoService = gameInfoService;
        this.messageSource = messageSource;
    }



    @GetMapping("/news")
    public ModelAndView selectAllNewsList(ModelAndView mv){
        List<NewsDTO> newsList = newsInfoService.selectAllNewsList();
        newsList.stream().forEach(newsDTO -> System.out.println("news =" + newsDTO) );
        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();


        mv.addObject("gameInfoList", gameInfoList);
        mv.addObject("newsList", newsList);
        mv.setViewName("news/news");

        return mv;
    }

    @ResponseBody
    @GetMapping(value = "/gameCodeNews",produces = "application/json")
    public ResponseEntity selectAllGameCodeNews(@RequestBody Map<String,Object> objectMap){


        Map<String,Object> map = new HashMap<>();
        System.out.println(objectMap);
        map = newsInfoService.selectAllGameCodeNews();

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
//    @ResponseBody
//    @PostMapping("/gameCodeNews")
//    public ModelAndView selectAllGameCodeNews(ModelAndView mv){
//
//        List<NewsDTO> gameCodeNews = newsInfoService.selectAllGameCodeNews();
//        mv.addObject("gameCodeNews", gameCodeNews);
//
//        return mv;
//    }

    @GetMapping("/newsDetail")
    public  ModelAndView newsDetail(ModelAndView mv, String newsCode){
        NewsDTO newsDTO = newsInfoService.newsDetail(newsCode);

        mv.addObject("detail", newsDTO);
        mv.setViewName("news/newsDetail");

        return mv;
    }


    @GetMapping("newsInsert")
    public void registPage(){}

    @PostMapping("newsInsert")
    public ModelAndView registNewsInfo(ModelAndView mv, NewsDTO newNewsInfo, RedirectAttributes rttr, Locale locale) throws Exception{
        newsInfoService.registNewsInfo(newNewsInfo);
        mv.setViewName("redirect:/news/news");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registNewsInfo", null, locale));


        return mv;
    }
    @GetMapping("newsUpdate")
    public ModelAndView updateNewsInfoForm(ModelAndView mv, HttpServletRequest request){
        String newsCode = request.getParameter("newsCode");
        NewsDTO newsDTO = newsInfoService.newsDetail(newsCode);
        mv.addObject("detail", newsDTO);
        mv.addObject("update", newsInfoService.getNewsCode(newsCode));
        mv.setViewName("news/newsUpdate");

        return mv;
    }

    @PostMapping("/newsUpdate")
    public  ModelAndView updateNewsInfo(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr, Locale locale) throws Exception {
        NewsDTO newsDTO = newsInfoService.newsDetail(request.getParameter("newsCode"));
        newsDTO.setNewsTitle(request.getParameter("newsTitle"));
        newsDTO.setNewsContent(request.getParameter("newsContent"));
        newsInfoService.updateNewsInfo(newsDTO);
        mv.setViewName("redirect:/news/news");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateNewsInfo", null, locale));

        return mv;
    }

    @GetMapping("/newsDelete")
    public ModelAndView deleteNewsInfo(ModelAndView mv, String newsCode){
        newsInfoService.deleteNewsInfo(newsCode);
        mv.setViewName("redirect:/news/news");

        return mv;
    }
}
