package com.semi.gamespace.common.model.service;

import com.semi.gamespace.common.handler.ImageHandler;
import com.semi.gamespace.common.model.dao.ImageDAO;
import com.semi.gamespace.common.model.dto.ImageDTO;
import com.semi.gamespace.config.ImageConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageDAO imageDAO;
    private final ImageHandler imageHandler;

    public ImageService(ImageDAO imageDAO, ImageHandler imageHandler) {
        this.imageDAO = imageDAO;
        this.imageHandler = imageHandler;
    }

    public ImageDTO selectProfileByCode(String memberCode) {
        ImageDTO image = imageDAO.selectProfileByCode(memberCode);
//        if(image == null) {
            //프로필 빈걸로 변경하면 파일 삭제하는 방향으로
//        }
        return image;
    }

    public boolean insertMemberProfile(ImageConfiguration imageConfig, MultipartFile file) {
        int result = 0;

        System.out.println(file.getOriginalFilename());

        int tmpDot = file.getOriginalFilename().lastIndexOf(".");
        String ext = file.getOriginalFilename().substring(tmpDot);  //확장자 얻기
        List<String> randomFileName = new ArrayList<>();
        randomFileName.add(UUID.randomUUID().toString().replace("-", "") + ext);

        ImageDTO image = imageHandler.imageParsing(imageConfig, file, randomFileName);
        if(image != null) {
            if(imageHandler.saveFile(imageConfig, file, randomFileName) > 0) {
                result += imageDAO.insertAttachImage(image);
            }
        }
        return result > 0;
    }
}
