package com.coursework.web.controller;

import com.coursework.web.dto.AddressDto;
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
    @PostMapping("/address")
    @ResponseBody
    public List<UserDto> saveAddress(@RequestBody AddressDto address){
        System.out.println(address);
        return null;
    }
}
