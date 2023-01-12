package com.semi.gamespace.news.model.dao;

import com.semi.gamespace.news.model.dto.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsInfoMapper {
    List<NewsDTO> selectAllNewsList();

    int registNewsInfo(NewsDTO newsDto);

    NewsDTO newsDetail(String newsCode);

    NewsDTO getNewsCode(String newscode);

    int updateNewsInfo(NewsDTO newsInfo);


    void deleteNewsInfo(String newsCode);

    Map<String,Object> selectAllGameCodeNews();
}
