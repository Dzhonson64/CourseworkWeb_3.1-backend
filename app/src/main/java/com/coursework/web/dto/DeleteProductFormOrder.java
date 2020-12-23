package com.coursework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductFormOrder {
    private Long orderId;
    private Long productId;
}
