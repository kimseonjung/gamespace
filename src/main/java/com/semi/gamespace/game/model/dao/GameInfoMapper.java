package com.semi.gamespace.game.model.dao;

import com.semi.gamespace.game.model.dto.GameInfoDTO;
import com.semi.gamespace.game.model.dto.MinimumSystemDTO;
import com.semi.gamespace.game.model.dto.RecommendedSystemDTO;
import com.semi.gamespace.game.model.dto.SpecificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameInfoMapper {

    List<GameInfoDTO> selectAllGameInfo();

    int registGameInfo(GameInfoDTO newGameInfo);

    List<MinimumSystemDTO> selectAllMinimumSystem();

    List<RecommendedSystemDTO> selectAllRecommendedSystem();

    int registMinimumSystem(MinimumSystemDTO newMinimumSystem);

    int registRecommendedSystem(RecommendedSystemDTO newRecommendedSystem);

    int registSpecification(SpecificationDTO newSpecification);
}
