package com.semi.gamespace.support.model.dao;

import com.semi.gamespace.support.model.dto.SupportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SupportMapper {

    List<SupportDTO> getList();

    SupportDTO getBoard(String supportCode);

    void uploadBoard(SupportDTO supportDTO);

    int updateBoard(SupportDTO supportDTO);

    void deleteBoard(String supportCode);
}
