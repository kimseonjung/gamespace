package com.semi.gamespace.free_gal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeGalDTO {
    private String freeGalCode;
    private String freeGalTitle;
    private String view;
    private Date freeGalDate;
    private String freeGalContent;
    private String memberCode;
    private String freeGalNotice;
    private String memberNickname;
    private String isAdmin;


}

