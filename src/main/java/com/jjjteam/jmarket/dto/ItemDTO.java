package com.jjjteam.jmarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDTO {
	
	private Long id;
    private String itemNm;
    private String itemIntroduction;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private String repimg;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
