package com.semi.gamespace.game.model.dao;

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
}