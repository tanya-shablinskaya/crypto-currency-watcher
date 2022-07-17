package com.shablinskaya.testproject.mapper;

import com.shablinskaya.testproject.repository.model.CryptoEntity;
import com.shablinskaya.testproject.service.modelDto.CryptoPriceDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface PriceCryptoMapper extends AbstractMapper<CryptoPriceDto, CryptoEntity> {
    @Override
    CryptoEntity toEntity(CryptoPriceDto cryptoPriceDto);

    @Override
    CryptoPriceDto toDto(CryptoEntity cryptoEntity);
}
