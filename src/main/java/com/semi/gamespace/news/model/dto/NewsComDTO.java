package com.semi.gamespace.news.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsComDTO {
    private String newsComCode;
    private String newsCode;
    private String newsCom;
    private String memberCode;
    private Date regDate;
    private String isAdmin;



}
