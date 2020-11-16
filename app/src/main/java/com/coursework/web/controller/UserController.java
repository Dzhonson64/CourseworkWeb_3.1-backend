package com.coursework.web.controller;

import com.coursework.web.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User", description = "обработка данныех пользователя")
@RequestMapping(value = "/user")
public class UserController {

    @Operation(summary = "Сохранение списка копирайтов")
    @PostMapping("/add")
    @ResponseBody
    public List<UserDto> save(@RequestBody List<UserDto> user){
        System.out.println(user);
        return null;
    }

    @Operation(summary = "Сохранение списка копирайтов")
    @GetMapping()
    @ResponseBody
    public List<UserDto> getUser(){
        System.out.println(21);
        return null;
    }
}
