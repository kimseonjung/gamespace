package com.semi.gamespace.free_gal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeGalComDTO {
    private String freeGalCode;
    private String freeGalComCode;
    private String freeGalComTitle;
    private String memberCode;
    private String memberNickname;
    private String freeGalComParent;

}
