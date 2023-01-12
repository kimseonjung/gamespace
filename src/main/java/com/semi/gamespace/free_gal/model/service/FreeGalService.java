package com.semi.gamespace.free_gal.model.service;

import com.semi.gamespace.free_gal.model.dao.FreeGalMapper;
import com.semi.gamespace.free_gal.model.dto.FreeGalComDTO;
import com.semi.gamespace.free_gal.model.dto.FreeGalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
//@Configuration
@Transactional(readOnly = true)

public class FreeGalService {

    //@PersistenceContext
    private final FreeGalMapper freeGalMapper;

    @Autowired
    public FreeGalService(FreeGalMapper freeGalMapper) {
        this.freeGalMapper = freeGalMapper;
    }

    public List<FreeGalDTO> getList() {
        return freeGalMapper.getList();
    }

    public List<FreeGalDTO> getListNotice() {
        return freeGalMapper.getListNotice();
    }

    public FreeGalDTO getBoard(String freeGalCode){
        return freeGalMapper.getBoard(freeGalCode);
    }

    @Transactional
    public void uploadBoard(FreeGalDTO freeGalDTO){
        freeGalMapper.uploadBoard(freeGalDTO);
    }

    @Transactional
    public int updateBoard(FreeGalDTO freeGalDTO) {
        int result = freeGalMapper.updateBoard(freeGalDTO);

        return result;
    }

    @Transactional
    public void deleteBoard(String freeGalCode) { freeGalMapper.deleteBoard(freeGalCode); }
    // 댓글 리스트 가져오기
    public List<FreeGalComDTO> getComment(String freeGalCode){ return freeGalMapper.getComment(freeGalCode);}

    //댓글 freeGalCode 넘기기, 댓글 작성 버튼으로 db에 넘긴 후 게시 글 상세보기 화면으로 새로고침 할 때 freeGalCode를 넘겨주기 위해
    public FreeGalComDTO getFreeGalCode(String freeGalCode){
        return freeGalMapper.getFreeGalCode(freeGalCode);
    }
    //댓글 업로드
    public void uploadComment(FreeGalComDTO freeGalComDTO){
        freeGalMapper.uploadComment(freeGalComDTO);
    }
    // 댓글 업데이트
    public void updateComment(FreeGalComDTO freeGalComDTO) {
        freeGalMapper.updateComment(freeGalComDTO);
    }

    // 댓글 삭제
    public void deleteComment(String freeGalComCode) {
        freeGalMapper.deleteComment(freeGalComCode);
    }

}
