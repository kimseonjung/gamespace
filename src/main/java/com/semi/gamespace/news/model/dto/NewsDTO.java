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
public class NewsDTO {

    private String newsCode;
    private String newsTitle;
    private String newsView;
    private Date newsDate;
    //private String newsDate;
    private String newsContent;
    private String gameName;
    private String memberName;

    private String isAdmin;

}