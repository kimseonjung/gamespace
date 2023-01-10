package com.semi.gamespace.game.model.service;

import com.semi.gamespace.game.model.dao.GameInfoMapper;
import com.semi.gamespace.game.model.dto.*;
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

    public boolean registGameInfo(GameInfoDTO gameInfo) throws Exception{

        int result = gameInfoMapper.registGameInfo(gameInfo);

        if (result <= 0){
            throw new Exception("신규게임정보추가등록실패");

        }
        return result > 0 ? true: false;
    }


    public MinimumSystemDTO selectAllMinimumSystem() {


        return gameInfoMapper.selectAllMinimumSystem();
    }

    public RecommendedSystemDTO selectAllRecommendedSystem() {

        return gameInfoMapper.selectAllRecommendedSystem();

    }

    public List<CategoryDTO> selectAllCategory(){

        return gameInfoMapper.selectAllCategory();

    }

    public List<TagDTO> selectAllTag(){

        return gameInfoMapper.selectAllTag();
    }

    public List<DevicesDTO> selectAllDevices(){

        return gameInfoMapper.selectAllDevices();

    }






    public boolean registMinimumSystem(MinimumSystemDTO newMinimumSystem) throws Exception{

        int result = gameInfoMapper.registMinimumSystem(newMinimumSystem);

        if (result <= 0){
            throw new Exception("신규최소시스템추가등록실패");

        }
        return result > 0 ? true: false;

    }

    public boolean registRecommendedSystem(RecommendedSystemDTO newRecommendedSystem)throws Exception{

        int result = gameInfoMapper.registRecommendedSystem(newRecommendedSystem);

        if (result <= 0){
            throw new Exception("신규권장시스템추가등록실패");

        }
        return result > 0 ? true: false;

    }

    public boolean registSpecification(SpecificationDTO newSpecification) throws Exception{

        int result = gameInfoMapper.registSpecification(newSpecification);

        if (result <= 0){
            throw new Exception("신규사양추가등록실패");

        }
        return result > 0 ? true: false;

    }

    public List<CategoryDTO> selectOneCategory(String categoryCode) {
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println(categoryCode);
        return gameInfoMapper.selectOneCategory(categoryCode);
    }
}
