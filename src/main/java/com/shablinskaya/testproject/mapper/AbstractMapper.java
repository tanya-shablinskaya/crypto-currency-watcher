package com.shablinskaya.testproject.mapper;

public interface AbstractMapper<Dto, Entity> {
    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);
}
