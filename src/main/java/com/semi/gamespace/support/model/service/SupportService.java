package com.semi.gamespace.support.model.service;


import com.semi.gamespace.support.model.dao.SupportMapper;
import com.semi.gamespace.support.model.dto.SupportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
//@Configuration
@Transactional(readOnly = true)
public class SupportService {

    private final SupportMapper supportMapper;

    @Autowired
    public SupportService(SupportMapper supportMapper) {
        this.supportMapper = supportMapper;
    }

    public List<SupportDTO> getList() {
        return supportMapper.getList();
    }

    public SupportDTO getBoard(String supportCode){
        return supportMapper.getBoard(supportCode);
    }
}
