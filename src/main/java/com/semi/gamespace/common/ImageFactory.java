package com.semi.gamespace.common;

import com.semi.gamespace.common.model.dto.ImageDTO;
import com.semi.gamespace.common.model.service.ImageService;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class ImageFactory {
    private List<ImageDTO> imageList;
    private final ImageService imageService;

    public ImageFactory(ImageService imageService) {
        this.imageService = imageService;
    }

    //초기화 - 리스트 선언
    public ImageFactory init() {
        imageList = new ArrayList<>();
        return this;
    }
    //리턴 - imageList 출력
    public List<ImageDTO> build() {
        return imageList;
    }


}
