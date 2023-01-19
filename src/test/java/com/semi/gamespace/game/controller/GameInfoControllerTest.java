package com.semi.gamespace.game.controller;

import com.semi.gamespace.config.GamespaceApplication;
import com.semi.gamespace.config.MybatisConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {GamespaceApplication.class, MybatisConfig.class, ContextConfiguration.class})
class GameInfoControllerTest {

    @Autowired
    private GameInfoController gameInfoController;

    private MockMvc mockMvc;

    @Test
    public void testInit(){
        assertNotNull(gameInfoController);
        assertNotNull(mockMvc);
    }

    @BeforeEach
    public void setup(){ mockMvc = MockMvcBuilders.standaloneSetup(gameInfoController).build();}

    @Test
    public void 전체_게임정보_조회용_컨트롤러_테스트_동작_확인() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders.get("game/gameInfo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("game/gameInfo"))
                .andDo(MockMvcResultHandlers.print());


    }
}