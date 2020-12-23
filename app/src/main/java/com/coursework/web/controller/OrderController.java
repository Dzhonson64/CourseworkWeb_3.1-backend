package com.coursework.web.controller;

import com.coursework.db.model.OrderProductEntity;
import com.coursework.mapper.OrderMapping;
import com.coursework.mapper.OrderProductMapper;
import com.coursework.service.OrderService;
import com.coursework.web.dto.BuyDto;
import com.coursework.web.dto.DeleteProductFormOrder;
import com.coursework.web.dto.OrderDto;
import com.coursework.web.dto.order.OrderProductDto;
import com.coursework.web.dto.order.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Tag(name = "OrderController", description = "Заказ")
@RequestMapping(value = "/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "")
    @PostMapping("/user/{id}")
    @ResponseBody
    public Long saveProductProperty(@PathVariable("id") Long id) {
        return orderService.getOrder(id).getId();
    }

    @Operation(summary = "")
    @GetMapping("/user/{id}")
    @ResponseBody
    public OrderResponseDto getOrder(@PathVariable("id") Long id) {
        return OrderMapping.MAPPER.toDto(orderService.getOrder(id));
    }

    @Operation(summary = "")
    @PostMapping("/{id}/buy")
    @ResponseBody
    public void buyOrder(@RequestBody BuyDto buyDto, @PathVariable("id") Long orderId) throws Exception {
        orderService.buy(buyDto, orderId);
    }

    @Operation(summary = "")
    @DeleteMapping("product")
    @ResponseBody
    public void buyOrder(@RequestParam("productId") Long productId, @RequestParam("orderId") Long orderId) throws Exception {
        orderService.deleteProductFromOrder(productId, orderId);
    }


    @Operation(summary = "")
    @GetMapping("all/{id}")
    @ResponseBody
    public List<OrderProductDto> getAllOrdersByUserId(@PathVariable("id") Long userId)  {
        String europeanDatePattern = "dd MMMM yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        List <OrderProductDto > o = OrderProductMapper.MAPPER.toListDto(orderService.getAllOrdersByUserId(userId));
        o.forEach(orderProductDto -> {
            orderProductDto.setOrderId(orderProductDto.getOrder().getId());
            orderProductDto.setDate(europeanDateFormatter.format(orderProductDto.getOrder().getLocalDateTime()));
        });
        return o;
    }


    @Operation(summary = "")
    @GetMapping("sales/{id}")
    @ResponseBody
    public List<OrderResponseDto> findAllByProvider(@PathVariable("id") Long userId)  {
        String europeanDatePattern = "dd MMMM yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        List <OrderResponseDto > o = OrderMapping.MAPPER.toListDto(orderService.findAllByProvider(userId));

        return o;
    }




}
