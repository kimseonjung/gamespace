package com.semi.gamespace.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsInfoController {
    @GetMapping("/news")
    public void newsMain(){}
}
