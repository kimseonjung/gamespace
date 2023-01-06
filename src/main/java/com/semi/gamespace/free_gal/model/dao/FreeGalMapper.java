package com.semi.gamespace.free_gal.model.dao;

import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


@Mapper
public interface FreeGalMapper {

    List<FreeGalDTO> getList();

}
