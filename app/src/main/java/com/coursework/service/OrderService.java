package com.coursework.service;

import com.coursework.db.model.*;
import com.coursework.db.model.type.Status;
import com.coursework.db.repository.OrderProductRepo;
import com.coursework.db.repository.OrderRepo;
import com.coursework.db.repository.ProviderRepo;
import com.coursework.db.repository.UserRepo;
import com.coursework.web.dto.BuyDto;
import com.coursework.web.dto.DeleteProductFormOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final OrderProductRepo orderProductRepo;
    private final ProviderRepo providerRepo;

    public OrderEntity getOrder(Long userId){

        UserEntity user = userRepo.findById(userId).orElse(null);
        Optional<OrderEntity> orderEntity = orderRepo.findByStatusAndUser(Status.ACTIVE, user);
        if (orderEntity.isPresent()) {
            return orderEntity.get();
        }
        OrderEntity newOrderEntity = new OrderEntity();
        newOrderEntity.setUser(user);
        newOrderEntity.setStatus(Status.ACTIVE);
        newOrderEntity.setLocalDateTime(LocalDateTime.now());
        return orderRepo.save(newOrderEntity);
    }

    public void buy(BuyDto buyDto, Long orderId) throws Exception {
        UserEntity user = userRepo.findById(buyDto.getUserId()).orElse(null);
        OrderEntity order = orderRepo.findById(orderId).orElse(null);
        if (buyDto.getTotalPrice().compareTo(user.getMoney()) > 0) {
            throw new Exception("Не хватает денежных средств, для совершения покупки");
        }
        user.setMoney(user.getMoney().subtract(buyDto.getTotalPrice()));
        order.setStatus(Status.UNABLE);
        userRepo.save(user);
        orderRepo.save(order);
    }

    public void deleteProductFromOrder( Long productId, Long orderId) throws Exception {
        OrderEntity order = orderRepo.findById(orderId).orElse(null);
        orderProductRepo.deleteAll(order.getOrderProductList().stream().filter(orderProductEntity -> orderProductEntity.getId().equals(productId)).collect(Collectors.toList()));
        //orderRepo.save(order);
    }

    public List<OrderProductEntity> getAllOrdersByUserId(Long userId)  {
        UserEntity user = userRepo.findById(userId).orElse(null);
        return orderProductRepo.getAllOrdersByUserId(user);
    }

    public List<OrderEntity> findAllByProvider(Long providerId)  {
        ProviderEntity providerEntity = providerRepo.findById(providerId).orElse(null);
        List<OrderEntity> orderEntities = orderRepo.findByProvider(providerEntity.getId());
        return orderEntities;
    }
}
