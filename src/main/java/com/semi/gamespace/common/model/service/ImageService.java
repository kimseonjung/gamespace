package com.semi.gamespace.common.model.service;

import com.semi.gamespace.common.model.dao.ImageDAO;
import com.semi.gamespace.common.model.dto.ImageDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageDAO imageDAO;

    public ImageService(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public ImageDTO selectProfileByCode(String memberCode) {
        ImageDTO image = imageDAO.selectProfileByCode(memberCode);
        if(image == null) {
            image = new ImageDTO();
            image.setSavePath("/image/icon/");
            image.setSaveName("mypage.svg");
        }
        return image;
    }

    public boolean countIfProfileIsExist(String memberCode) {
        int result = 0;
        try {
            result = imageDAO.countIfProfileIsExist(memberCode);
        } catch (Exception e) {
            System.out.println("유저 프로필 목록 조회 중 오류가 발생했습니다.");
        }
        return result > 0 ? true : false;
    }

    public boolean insertProfileImage(ImageDTO image) {
        int result = 0;
        List<ImageDTO> imageList = new ArrayList<>();
        imageList.add(image);

        try {
            for(ImageDTO targetImage : imageList) {
                result = imageDAO.insertAttachImage(targetImage);
            }
            System.out.println("이미지 등록 결과 : " + result);
        } catch (Exception e) {
            System.out.println("이미지 등록 중 오류가 발생했습니다.");
        }
        return result > 0 ? true : false;
    }
}
