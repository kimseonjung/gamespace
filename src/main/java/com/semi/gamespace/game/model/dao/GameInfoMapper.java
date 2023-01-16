package com.semi.gamespace.game.model.dao;

import com.semi.gamespace.game.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameInfoMapper {

    List<GameInfoDTO> selectAllGameInfo();

    int registGameInfo(GameInfoDTO newGameInfo);

    MinimumSystemDTO selectAllMinimumSystem(String gameCode);

    RecommendedSystemDTO selectAllRecommendedSystem( String gameCode);

    int registMinimumSystem(MinimumSystemDTO newMinimumSystem);

    int registRecommendedSystem(RecommendedSystemDTO newRecommendedSystem);

    int registSpecification(SpecificationDTO newSpecification);

    List<CategoryDTO> selectAllCategory();

    List<TagDTO> selectAllTag();

    List<DevicesDTO> selectAllDevices();



    List<CategoryDTO> selectCheckCategoryTag(Map<String, List<String>> categoryCode);

    List<GameInfoDTO> selectCategoryOne(String cateNo);

    GameInfoDTO selectGameDetail(String gameCode);

    GameInfoDTO getGameCode(String gamecode);

    int updateGameInfo(GameInfoDTO gameInfoDetail);
}
