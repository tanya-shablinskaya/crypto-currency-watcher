package com.shablinskaya.testproject.service.modelDto;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class CryptoInfoDto {
    private String symbol;
}
