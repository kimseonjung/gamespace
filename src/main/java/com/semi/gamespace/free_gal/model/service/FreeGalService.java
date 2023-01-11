package com.semi.gamespace.free_gal.model.service;

import com.semi.gamespace.free_gal.model.dao.FreeGalMapper;
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

}
