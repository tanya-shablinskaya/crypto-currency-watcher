package com.shablinskaya.testproject.service;

import com.shablinskaya.testproject.repository.model.UserEntity;
import com.shablinskaya.testproject.service.modelDto.UserInfoDto;

import java.util.List;

public interface UserService {

    UserInfoDto userNotify(String username, String code);

    List<UserEntity> findByUsersByCode(String code);

}
