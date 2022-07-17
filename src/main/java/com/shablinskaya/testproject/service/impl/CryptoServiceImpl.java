package com.shablinskaya.testproject.service.impl;

import com.shablinskaya.testproject.exception.CustomException;
import com.shablinskaya.testproject.mapper.CryptoMapper;
import com.shablinskaya.testproject.mapper.PriceCryptoMapper;
import com.shablinskaya.testproject.repository.CryptoRepository;
import com.shablinskaya.testproject.repository.model.CryptoEntity;
import com.shablinskaya.testproject.service.CryptoService;
import com.shablinskaya.testproject.service.modelDto.CryptoInfoDto;
import com.shablinskaya.testproject.service.modelDto.CryptoPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {
    private final CryptoRepository repository;
    private final CryptoMapper cryptoMapper;
    private final PriceCryptoMapper priceMapper;


    @Override
    public List<CryptoInfoDto> findAll(Integer pageNo, Integer pageSize) {

        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<CryptoEntity> pagedResult = repository.findAll(page);
        return pagedResult.getContent().stream()
                .map(cryptoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CryptoPriceDto findByPrice(String symbol) {
        return priceMapper.toDto(repository.findBySymbol(symbol)
                .orElseThrow(() -> new CustomException(String.format("could not get crypto with code: %s", symbol))));
    }

    @Override
    @Transactional
    public void update(Long id, Float price) {
        repository.findById(id)
                .orElseThrow(() -> new CustomException(String.format("This id % was not found", id)));
        repository.update(id, price);
    }
}