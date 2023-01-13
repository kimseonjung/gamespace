package com.semi.gamespace.free_gal.model.dao;

import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import com.semi.gamespace.free_gal.model.dto.FreeGalComDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


@Mapper
public interface FreeGalMapper {

    List<FreeGalDTO> getList();

    List<FreeGalDTO> getListNotice();

    FreeGalDTO getBoard(String freeGalCode);

    void uploadBoard(FreeGalDTO freeGalDTO);

    int updateBoard(FreeGalDTO freeGalDTO);

    void deleteBoard(String freeGalCode);

    // 댓글 조회
    List<FreeGalComDTO> getComment(String freeGalCode);

    // 댓글 freeGalCode 넘기기
    FreeGalComDTO getFreeGalCode(String freeGalCode);

    // 댓글 작성
    void uploadComment(FreeGalComDTO freeGalComDTO);

    // 댓글 수정
    void updateComment(FreeGalComDTO freeGalComDTO);

    // 댓글 삭제
    void deleteComment(String freeGalComCode);

    // 대댓글 작성
    void uploadCommentComment(FreeGalComDTO freeGalComDTO);

    // 대댓글 조회
    List<FreeGalComDTO> getCommentComment(String freeGalCode);

    // 대댓글 수정
    void updateCommentComment(FreeGalComDTO freeGalComDTO);

    // 대댓글 삭제
    void deleteCommentComment(String freeGalComCode);

}
