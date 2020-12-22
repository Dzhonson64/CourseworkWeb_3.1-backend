package com.coursework.mapper;

import com.coursework.db.model.OrderEntity;
import com.coursework.db.model.OrderProductEntity;
import com.coursework.web.dto.order.OrderProductDto;
import com.coursework.web.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface OrderProductMapper {
    OrderProductMapper MAPPER = Mappers.getMapper(OrderProductMapper.class);

    OrderProductDto toDto(OrderProductEntity source);
    List<OrderProductDto> toListDto(List<OrderProductEntity> source);
}
