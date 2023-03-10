package com.semi.gamespace.game.model.service;

import com.semi.gamespace.game.model.dao.GameInfoMapper;
import com.semi.gamespace.game.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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


    public MinimumSystemDTO selectDetailMinimumSystem(String gameCode) {


        return gameInfoMapper.selectDetailMinimumSystem(gameCode);
    }

    public RecommendedSystemDTO selectDetailRecommendedSystem(String gameCode) {

        return gameInfoMapper.selectDetailRecommendedSystem(gameCode);

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

    public List<CategoryDTO> selectCheckCategoryTag(Map<String, List<String>> categoryCode) {

        return gameInfoMapper.selectCheckCategoryTag(categoryCode);
    }

    public List<GameInfoDTO> selectCategoryOne(String cate_no) {

        return gameInfoMapper.selectCategoryOne(cate_no);

    }


    public GameInfoDTO selectGameDetail(String gameCode) {

        return gameInfoMapper.selectGameDetail(gameCode);
    }

    public  GameInfoDTO getGameCode(String gamecode) {
        return gameInfoMapper.getGameCode(gamecode);
    }

    public int updateGameInfo(GameInfoDTO gameInfoDetail) {
        int result = gameInfoMapper.updateGameInfo(gameInfoDetail);
        return result;
    }

    public boolean registDebugGameInfo(GameInfoDTO gameInfo) throws Exception{
        int result = gameInfoMapper.registDebugGameInfo(gameInfo);

        if (result <= 0){
            throw new Exception("신규게임정보추가등록실패");

        }
        return result > 0 ? true: false;
    }

    public boolean registDebugSpecification(SpecificationDTO specification) throws Exception{

        int result = gameInfoMapper.registDebugSpecification(specification);

        if (result <= 0){
            throw new Exception("신규사양추가등록실패");

        }
        return result > 0 ? true: false;
    }

    public boolean registDebugMinimumSystem(MinimumSystemDTO minimumSystem) throws Exception{
        int result = gameInfoMapper.registDebugMinimumSystem(minimumSystem);

        if (result <= 0){
            throw new Exception("신규최소시스템추가등록실패");

        }
        return result > 0 ? true: false;
    }

    public boolean registDebugRecommendedSystem(RecommendedSystemDTO recommendedSystem) throws Exception{

        int result = gameInfoMapper.registDebugRecommendedSystem(recommendedSystem);

        if (result <= 0){
            throw new Exception("신규권장시스템추가등록실패");

        }
        return result > 0 ? true: false;
    }
}
