package com.coursework.service;

import com.coursework.db.model.*;
import com.coursework.db.model.type.Status;
import com.coursework.db.repository.*;
import com.coursework.mapper.UserMapper;
import com.coursework.web.dto.OrderDto;
import com.coursework.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ProductRepo productRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepo addressRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;


    @Override
    public UserEntity register(UserEntity user) {
        RoleEntity roleUser = roleRepo.findByName("ROLE_USER");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setMoney(new BigDecimal(0));
        user.setStatus(Status.ACTIVE);
        AddressEntity addressEntity = addressRepo.save(user.getAddress());
        user.setAddress(addressEntity);
        UserEntity registeredUser = userRepo.save(user);


        return registeredUser;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> result = userRepo.findAll();
        return result;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity result = userRepo.findByUsername(username);
        return result;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity result = userRepo.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }


    public void orderProduct(OrderDto orders, Long orderId) {
        Optional<UserEntity> user = userRepo.findById(orders.getUserId());
        Optional<ProductEntity> product = productRepo.findById(orders.getProductId());
        Optional<OrderEntity> order = orderRepo.findById(orderId);

        if (user.isPresent() && order.isPresent() && product.isPresent()) {
                OrderProductEntity orderProductEntity = new OrderProductEntity();
                orderProductEntity.setProduct(product.get());
                orderProductEntity.setOrder(order.get());
                orderProductEntity.setAmount(orders.getAmount());

                order.get().setUser(user.get());
                order.get().getOrderProductList().add(orderProductEntity);
                orderProductRepo.save(orderProductEntity);
                orderRepo.save(order.get());
                productRepo.save(product.get());
                userRepo.save(user.get());
        }

    }


    public void updateUser(UserDto userDto) {
        UserEntity entity = UserMapper.MAPPER.toEntity(userDto);
        UserEntity userEntity = userRepo.findById(userDto.getId()).orElse(null);
        AddressEntity addressEntity = addressRepo.findById(userEntity.getAddress().getId()).orElse(null);
        BeanUtils.copyProperties(entity.getAddress(), addressEntity,"id", "apartment", "userList", "addressList", "type");
        addressRepo.save(entity.getAddress());
        BeanUtils.copyProperties(entity, userEntity,"id", "password", "male", "address", "roles", "orderList", "feedbackList");
        userRepo.save(entity);
    }
}
