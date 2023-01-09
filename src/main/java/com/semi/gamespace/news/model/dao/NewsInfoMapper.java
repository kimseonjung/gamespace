package com.semi.gamespace.news.model.dao;

import com.semi.gamespace.news.model.dto.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsInfoMapper {
    List<NewsDTO> selectAllNewsList();

    int registNewsInfo(NewsDTO newsDto);

}
