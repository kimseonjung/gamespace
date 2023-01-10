package com.semi.gamespace.game.model.dao;

import com.semi.gamespace.game.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameInfoMapper {

    List<GameInfoDTO> selectAllGameInfo();

    int registGameInfo(GameInfoDTO newGameInfo);

    MinimumSystemDTO selectAllMinimumSystem();

    RecommendedSystemDTO selectAllRecommendedSystem();

    int registMinimumSystem(MinimumSystemDTO newMinimumSystem);

    int registRecommendedSystem(RecommendedSystemDTO newRecommendedSystem);

    int registSpecification(SpecificationDTO newSpecification);

    List<CategoryDTO> selectAllCategory();

    List<TagDTO> selectAllTag();

    List<DevicesDTO> selectAllDevices();


    List<CategoryDTO> selectOneCategory(String categoryCode);
}
