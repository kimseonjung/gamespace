package com.semi.gamespace.news.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semi.gamespace.game.model.dto.CategoryDTO;
import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.dto.TagDTO;
import com.semi.gamespace.game.model.service.GameInfoService;
import com.semi.gamespace.news.model.dto.NewsComDTO;
import com.semi.gamespace.news.model.dto.NewsDTO;
import com.semi.gamespace.news.model.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        newsList.stream().forEach(newsDTO -> System.out.println("news = " + newsDTO) );
        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();
        gameInfoList.stream().forEach(GameInfoDTO -> System.out.println("games = " + GameInfoDTO) );


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
//        List<NewsComDTO> newsComDTO = newsInfoService.getNewsCom(newsCode);
//        System.out.println(newsComDTO.get(0));

        mv.addObject("newsComList",newsInfoService.getNewsCom(newsCode));

        mv.addObject("detail", newsDTO);
        mv.setViewName("news/newsDetail");

        return mv;
    }
    @PostMapping("/uploadNewsCom")
    public  ModelAndView uploadNewsCom(ModelAndView mv, NewsComDTO newsComDTO,String newsCode){
        newsInfoService.uploadNewsCom(newsComDTO);

        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
        return mv;
    }


    @PostMapping("/newsDetail")
    public  ModelAndView newsDetail(ModelAndView mv, NewsComDTO newsComDTO,String newsCode){

        Map<String, String> newsCom = new HashMap<String, String>();
        newsCom.put("newsComCode",newsComDTO.getNewsComCode());
        newsCom.put("newsCode",newsCode);
        newsCom.put("newsCom",newsComDTO.getNewsCom());

        newsInfoService.updateNewsCom(newsCom);



        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
        return mv;
    }
    @PostMapping("/deleteNewsCom")
    public  ModelAndView deleteNewsCom(ModelAndView mv, String newsComCode,String newsCode){


        newsInfoService.deleteNewsCom(newsComCode);

        mv.setViewName("redirect:/news/newsDetail?newsCode="+newsCode);
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


    @PostMapping(value = "tag", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String gameCodeNewsList(Model model, NewsDTO newsDTO,
                                         @RequestParam(value = "gameCode[]")List<String> gameCode) throws Exception{

        gameCode.remove(0);
        System.out.println(newsDTO);

        Map<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("gameCode", gameCode.isEmpty() ? null : gameCode);
        List<GameInfoDTO> gameCodeNewsList = newsInfoService.gameCodeNewsList(dataMap);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("gameCodeNewsList", gameCodeNewsList);

        String jsonString = gson.toJson(map);

        System.out.println(jsonString);

        //mv.addObject("categoryList", gson.toJson(categoryList));
        //mv.setViewName("jsonView");

        return jsonString;
    }





}
