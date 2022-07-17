package com.shablinskaya.testproject.mapper;

import com.shablinskaya.testproject.repository.model.CryptoEntity;
import com.shablinskaya.testproject.service.modelDto.CryptoInfoDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CryptoMapper extends AbstractMapper<CryptoInfoDto, CryptoEntity> {
    @Override
    CryptoEntity toEntity(CryptoInfoDto cryptoInfoDto);

    @Override
    CryptoInfoDto toDto(CryptoEntity cryptoEntity);
}
