package com.laundrygo.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlResDto {
    private String originalUrl;
    private String shortUrl;
    private Integer requestCount;
}
