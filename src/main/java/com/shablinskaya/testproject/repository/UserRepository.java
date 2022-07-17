package com.shablinskaya.testproject.repository;

import com.shablinskaya.testproject.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findBySymbol(String code);
}
