package com.coursework.web.controller;

import com.coursework.db.repository.UserRepo;
import com.coursework.mapper.UserMapper;
import com.coursework.service.UserService;
import com.coursework.service.UserServiceImpl;
import com.coursework.web.dto.AddressDto;
import com.coursework.web.dto.OrderDto;
import com.coursework.web.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@Tag(name = "User", description = "обработка данныех пользователя")
@RequestMapping(value = "/user")
public class UserController {
    private final UserServiceImpl userService;


    @Operation(summary = "Сохранение списка копирайтов")
    @PostMapping("/address")
    @ResponseBody
    public List<UserDto> saveAddress(@RequestBody AddressDto address) {
        System.out.println(address);
        return null;
    }

    @Operation(summary = "Сохранение списка копирайтов")
    @PostMapping("/order/bucket/{id}")
    @ResponseBody
    public void orderProduct(@PathVariable("id") Long bucketId, @RequestBody OrderDto orderDto) {

        userService.orderProduct(orderDto, bucketId);
    }


    @Operation(summary = "Сохранение списка копирайтов")
    @GetMapping("/{id}")
    @ResponseBody
    public UserDto orderProduct(@PathVariable("id") Long userId) {
        return UserMapper.MAPPER.toDto(userService.findById(userId));
    }



    @Operation(summary = "Сохранение списка копирайтов")
    @PostMapping("")
    @ResponseBody
    public void getUser(@RequestBody UserDto user) {
        userService.updateUser(user);
    }


}
