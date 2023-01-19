package com.semi.gamespace.game.model.dao;

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
class GameInfoMapperTest {

    @Autowired
    private GameInfoMapper gameInfoMapper;

    @Test
    public void 메터_인터페이스_의존성_주입_테스ㅡ(){ assertNotNull(gameInfoMapper);}

    @Test
    public void 전체_게임정보_조회용_메퍼_테스트() {

        List<GameInfoDTO> gameInfoList= gameInfoMapper.selectAllGameInfo();

        assertNotNull(gameInfoList);

    }

    @Test
    public void 신규_게임정보_추가용_메퍼_테스트(){

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

        int result = gameInfoMapper.registGameInfo(game);

        assertEquals(1,result);



    }




}