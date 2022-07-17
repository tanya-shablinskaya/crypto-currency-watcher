package com.shablinskaya.testproject.service;

import com.shablinskaya.testproject.service.modelDto.CryptoInfoDto;
import com.shablinskaya.testproject.service.modelDto.CryptoPriceDto;

import java.util.List;

public interface CryptoService {

    List<CryptoInfoDto> findAll(Integer pageNo, Integer pageSize);

    CryptoPriceDto findByPrice(String symbol);

    void update(Long id, Float price);
}
