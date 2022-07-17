package com.shablinskaya.testproject.controller;

import com.shablinskaya.testproject.service.CryptoService;
import com.shablinskaya.testproject.service.UserService;
import com.shablinskaya.testproject.service.modelDto.CryptoInfoDto;
import com.shablinskaya.testproject.service.modelDto.CryptoPriceDto;
import com.shablinskaya.testproject.service.modelDto.UserInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/crypto-info")
@AllArgsConstructor
public class CryptoController {
    private CryptoService cryptoService;
    private UserService userService;

    @GetMapping
    List<CryptoInfoDto> getAll(@RequestParam(defaultValue = "0") final Integer pageNo,
                               @RequestParam(defaultValue = "10") final Integer pageSize) {
        return cryptoService.findAll(pageNo, pageSize);
    }

    @GetMapping(value = "/{code}")
    CryptoPriceDto findByPriceByCode(@PathVariable String code) {
        return cryptoService.findByPrice(code.toUpperCase());
    }

    @PostMapping(value = "/notify")
    @ResponseStatus(HttpStatus.CREATED)
    UserInfoDto notify(@RequestParam(name = "username") String username,
                       @RequestParam(name = "code") String code) {
        return userService.userNotify(username, code);
    }

}
