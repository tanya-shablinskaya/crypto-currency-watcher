package com.shablinskaya.testproject.service.impl;

import com.shablinskaya.testproject.exception.CustomException;
import com.shablinskaya.testproject.mapper.UserMapper;
import com.shablinskaya.testproject.repository.CryptoRepository;
import com.shablinskaya.testproject.repository.UserRepository;
import com.shablinskaya.testproject.repository.model.UserEntity;
import com.shablinskaya.testproject.service.UserService;
import com.shablinskaya.testproject.service.modelDto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CryptoRepository cryptoRepository;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserInfoDto userNotify(String username, String code) {
        Float currentPrice = cryptoRepository.findBySymbol(code)
                .orElseThrow(() -> new CustomException(String.format("could not get crypto currency with code: %s", code)))
                .getPrice();
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .username(username)
                .symbol(code)
                .price(currentPrice)
                .build();

        return mapper.toDto(userRepository.save(mapper.toEntity(userInfoDto)));
    }

    @Override
    public List<UserEntity> findByUsersByCode(String code) {
        return userRepository.findBySymbol(code);
    }
}
