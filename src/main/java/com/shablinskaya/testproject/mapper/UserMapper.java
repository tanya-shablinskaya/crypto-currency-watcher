package com.shablinskaya.testproject.mapper;

import com.shablinskaya.testproject.repository.model.UserEntity;
import com.shablinskaya.testproject.service.modelDto.UserInfoDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper extends AbstractMapper<UserInfoDto, UserEntity> {
    @Override
    UserEntity toEntity(UserInfoDto dto);

    @Override
    UserInfoDto toDto(UserEntity entity);
}
