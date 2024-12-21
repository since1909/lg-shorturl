package com.laundrygo.shorturl.mapper;

import com.laundrygo.shorturl.dto.UrlResDto;
import com.laundrygo.shorturl.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {
    public UrlResDto toDto(Url url) {
        return new UrlResDto(url.getOriginalUrl(), url.getShortUrl(), url.getRequestCount());
    }
}
