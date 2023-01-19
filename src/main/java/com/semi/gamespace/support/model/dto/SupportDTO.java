package com.semi.gamespace.support.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupportDTO {

    private String supportCode;
    private String supportTitle;
    private String supportView;
    private Date supportDate;
    private String supportContent;
    private String memberCode;
    private String memberNickname;
    private String isAdmin;

}
