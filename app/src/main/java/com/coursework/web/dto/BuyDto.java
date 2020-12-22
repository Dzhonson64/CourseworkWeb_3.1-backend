package com.coursework.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BuyDto {
    private BigDecimal totalPrice;
    private Long userId;
}
