package com.semi.gamespace.common.model.dao;

import com.semi.gamespace.common.model.dto.ImageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageDAO {
    int countIfProfileIsExist(String memberCode);
    int insertAttachImage(ImageDTO image);

    ImageDTO selectProfileByCode(String memberCode);
}
