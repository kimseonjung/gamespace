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

}
