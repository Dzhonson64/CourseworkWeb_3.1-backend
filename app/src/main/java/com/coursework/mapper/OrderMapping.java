package com.coursework.mapper;

import com.coursework.db.model.OrderEntity;
import com.coursework.web.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapping {
    OrderMapping MAPPER = Mappers.getMapper(OrderMapping.class);

    @Mapping(target = "date", expression = "java(java.time.format.DateTimeFormatter.ofPattern(\"dd MMMM yyyy\").format(source.getLocalDateTime()))")
    OrderResponseDto toDto(OrderEntity source);

    List<OrderResponseDto> toListDto(List<OrderEntity> source);
}
