package com.coursework.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long productId;
    private Long userId;
    private Integer amount;
    private String date;
    private Long orderId;
}
