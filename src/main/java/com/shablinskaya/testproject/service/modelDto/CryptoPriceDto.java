package com.shablinskaya.testproject.service.modelDto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CryptoPriceDto {
    private String symbol;
    private Float price;
}
