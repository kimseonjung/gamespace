package com.semi.gamespace.news.controller;

import com.semi.gamespace.news.model.dto.NewsDTO;
import com.semi.gamespace.news.model.service.NewsInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/news", "/"})
public class NewsInfoController {

    private final NewsInfoService newsInfoService;

    public NewsInfoController(NewsInfoService newsInfoService) {
        this.newsInfoService = newsInfoService;
    }



    @GetMapping("/news")
    public ModelAndView selectAllNewsList(ModelAndView mv){
        List<NewsDTO> newsList = newsInfoService.selectAllNewsList();
        newsList.stream().forEach(newsDTO -> System.out.println("news =" + newsDTO) );

        mv.addObject("newsList", newsList);
        mv.setViewName("news/news");

        return mv;
    }
}
