package com.semi.gamespace.news.controller;

import com.semi.gamespace.news.model.dto.NewsDTO;
import com.semi.gamespace.news.model.service.NewsInfoService;
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
@RequestMapping(value = {"/news", "/"})
public class NewsInfoController {

    private final NewsInfoService newsInfoService;
    private  final MessageSource messageSource;

    @Autowired
    public NewsInfoController(NewsInfoService newsInfoService, MessageSource messageSource) {
        this.newsInfoService = newsInfoService;
        this.messageSource = messageSource;
    }



    @GetMapping("/news")
    public ModelAndView selectAllNewsList(ModelAndView mv){
        List<NewsDTO> newsList = newsInfoService.selectAllNewsList();
        newsList.stream().forEach(newsDTO -> System.out.println("news =" + newsDTO) );

        mv.addObject("newsList", newsList);
        mv.setViewName("news/news");

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
}
