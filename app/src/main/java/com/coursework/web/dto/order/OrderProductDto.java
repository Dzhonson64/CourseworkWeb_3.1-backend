package com.coursework.web.dto.order;

import com.coursework.db.model.OrderEntity;
import com.coursework.web.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderProductDto {
    private Long id;
    private Integer amount;
    private ProductDto product;
    private String date;
    private Long orderId;
    @JsonIgnore
    private OrderEntity order;
}
