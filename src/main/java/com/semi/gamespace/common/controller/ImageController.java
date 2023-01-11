package com.semi.gamespace.common.controller;

import com.semi.gamespace.common.ImageFactory;
import com.semi.gamespace.common.model.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageController {
//    private final ImageService imageService;
//
//    @Autowired
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }

//    @GetMapping("/member/profile")
//    public ModelAndView getProfileImage(ModelAndView mv, HttpServletRequest request) {
//        ImageFactory myImage = new ImageFactory(imageService);
//        myImage.init();
//
//        mv.addObject("profile", null);
//        mv.setViewName("redirect:/");
//
//        return model;
//    }
}
