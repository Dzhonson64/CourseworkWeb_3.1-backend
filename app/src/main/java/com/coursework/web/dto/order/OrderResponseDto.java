package com.coursework.web.dto.order;

import com.coursework.db.model.OrderEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private List<OrderProductDto> orderProductList;
    private String date;
    @JsonIgnore
    private OrderEntity order;
}
