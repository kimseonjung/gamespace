package com.semi.gamespace.game.model.service;

import com.semi.gamespace.game.model.dao.GameInfoMapper;
import com.semi.gamespace.game.model.dto.DevicesDTO;
import com.semi.gamespace.game.model.dto.GameInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GameInfoService {

    private final GameInfoMapper gameInfoMapper;

    @Autowired
    public GameInfoService(GameInfoMapper gameInfoMapper) {
        this.gameInfoMapper = gameInfoMapper;
    }

    public List<GameInfoDTO> selectAllGameInfo() {

        return gameInfoMapper.selectAllGameInfo();

    }

}
