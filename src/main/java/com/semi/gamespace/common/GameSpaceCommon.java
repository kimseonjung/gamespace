package com.semi.gamespace.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class GameSpaceCommon {
    @GetMapping("/index")
    public void indexMain(){}
}
