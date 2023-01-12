package com.semi.gamespace.news.model.service;

import com.semi.gamespace.game.model.dao.GameInfoMapper;
import com.semi.gamespace.news.model.dao.NewsInfoMapper;
import com.semi.gamespace.news.model.dto.NewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsInfoService {

    private final NewsInfoMapper newsInfoMapper;

    public NewsInfoService(NewsInfoMapper newsInfoMapper) {
        this.newsInfoMapper = newsInfoMapper;
    }

    public List<NewsDTO> selectAllNewsList() {
        return newsInfoMapper.selectAllNewsList();
    }

    public boolean registNewsInfo(NewsDTO newsDto) throws Exception{
        int result = newsInfoMapper.registNewsInfo(newsDto);

        if (result <= 0){
            throw new Exception("신규게임정보추가등록실패");

        }
        return result > 0 ? true: false;
    }

    public NewsDTO newsDetail(String newsCode) {
        return newsInfoMapper.newsDetail(newsCode);
    }

    public NewsDTO getNewsCode(String newscode) {
        return newsInfoMapper.getNewsCode(newscode);
    }

    public int updateNewsInfo(NewsDTO newsInfo) {
        int result = newsInfoMapper.updateNewsInfo(newsInfo);
        return result;
    }


    public void deleteNewsInfo(String newsCode) {
        newsInfoMapper.deleteNewsInfo(newsCode);
    }

    public Map<String,Object> selectAllGameCodeNews() {
        return newsInfoMapper.selectAllGameCodeNews();
    }
}
