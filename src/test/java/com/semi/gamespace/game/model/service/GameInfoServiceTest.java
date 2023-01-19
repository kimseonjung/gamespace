package com.semi.gamespace.game.model.service;

import com.semi.gamespace.config.GamespaceApplication;
import com.semi.gamespace.config.MybatisConfig;
import com.semi.gamespace.game.model.dto.GameInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
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

    @Test
    public void 신규_게임정보_추가용_서비스_성공테스트()throws Exception{

        GameInfoDTO game = new GameInfoDTO();
        game.setGameCode("GAM_2");
        game.setGameName("더미게임명2");
        game.setDevicesCode("DEV_2");
        game.setCategoryCode("CAT_2");
        game.setTagCode("TAG_2");
        game.setRegistrationDate(new Date());
        game.setLaunchDate("2023-01-04");
        game.setDeveloper("더비개발자2");
        game.setDistributorCode("DIS_2");
        game.setRatingCode("RAT_2");
        game.setPrice("20000원");
        game.setPlatformCode("PLA_2");
        game.setLanguageCode("LAN_2");
        game.setSpecificationCode("SPE_1");
        game.setGameIntro("더미게임소개2입니다");
        game.setGameStatus("Y");

        boolean result = gameInfoService.registGameInfo(game);

        assertTrue(result);

    }

    @Test
    public void 신규_게임정보_추가용_서비스_실패테스트()throws Exception{

        GameInfoDTO game = new GameInfoDTO();
        game.setGameCode("GAM_2");
        game.setGameName("더미게임명2");
        game.setDevicesCode("DEV_2");
        game.setCategoryCode("CAT_2");
        game.setTagCode("TAG_2");
        game.setRegistrationDate(new Date());
        game.setLaunchDate("2023-01-04");
        game.setDeveloper("더비개발자2");
        game.setDistributorCode("DIS_2");
        game.setRatingCode("RAT_2");
        game.setPrice("20000원");
        game.setPlatformCode("PLA_2");
        game.setLanguageCode("LAN_2");
        game.setSpecificationCode("SPE_1");
        game.setGameIntro("더미게임소개2입니다");
        game.setGameStatus("Y");



        assertThrows(Exception.class, () -> gameInfoService.registGameInfo(game), "신규게임정보추가등록실패");


    }





}