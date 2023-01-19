package com.semi.gamespace.common.model.dao;

import com.semi.gamespace.common.model.dto.ImageDTO;
import com.semi.gamespace.config.ImageConfiguration;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface ImageDAO {
//    int countIfProfileIsExist(String memberCode);
    int insertAttachImage(ImageDTO image);

    ImageDTO selectProfileByCode(String memberCode);

    void deleteAttachImage(ImageConfiguration imageConfig, MultipartFile file, List<String> randomFileName);
}
