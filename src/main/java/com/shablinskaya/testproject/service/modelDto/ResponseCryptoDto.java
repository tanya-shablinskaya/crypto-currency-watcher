package com.shablinskaya.testproject.service.modelDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResponseCryptoDto {
    private Long id;
    private String symbol;
    private Float price;
}
