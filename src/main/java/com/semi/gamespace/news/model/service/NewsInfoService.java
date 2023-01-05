package com.semi.gamespace.news.model.service;

import com.semi.gamespace.game.model.dao.GameInfoMapper;
import com.semi.gamespace.news.model.dao.NewsInfoMapper;
import com.semi.gamespace.news.model.dto.NewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
