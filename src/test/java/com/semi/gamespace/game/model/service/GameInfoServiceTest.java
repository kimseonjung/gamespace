package com.semi.gamespace.game.model.service;

import com.semi.gamespace.config.GamespaceApplication;
import com.semi.gamespace.config.MybatisConfig;
import com.semi.gamespace.game.model.dto.GameInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {GamespaceApplication.class, MybatisConfig.class, ContextConfiguration.class})
class GameInfoServiceTest {

    @Autowired
    private GameInfoService gameInfoService;

    public  void testInit() { assertNotNull(gameInfoService);}



    @Test
    public void 전체_게임정보_조회용_서비스_테스트() {


        List<GameInfoDTO> gameInfoList = gameInfoService.selectAllGameInfo();

        assertNotNull(gameInfoList);
        gameInfoList.forEach(game -> System.out.println("game =" + game));


    }
}