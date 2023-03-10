package com.semi.gamespace.news.model.dao;

import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.news.model.dto.NewsComDTO;
import com.semi.gamespace.news.model.dto.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsInfoMapper {
    List<NewsDTO> selectAllNewsList(Map<String, String> search);
    int countAllNewsList(Map<String, String> search);

    int registNewsInfo(NewsDTO newsDto);

    NewsDTO newsDetail(String newsCode);

    NewsDTO getNewsCode(String newscode);

    int updateNewsInfo(NewsDTO newsInfo);


    void deleteNewsInfo(String newsCode);

    Map<String,Object> selectAllGameCodeNews();

    List<NewsComDTO> getNewsCom(String newsCode);

    int uploadNewsCom(NewsComDTO newsComDTO);

    int updateNewsCom(Map<String, String> newsCom);

    void deleteNewsCom(String newsComCode);

    List<GameInfoDTO> gameCodeNewsList(Map<String, List<String>> gameCode);
}
