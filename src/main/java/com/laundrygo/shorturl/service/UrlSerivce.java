package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.dto.UrlResDto;
import com.laundrygo.shorturl.entity.Url;
import com.laundrygo.shorturl.exception.UrlNotFoundException;
import com.laundrygo.shorturl.mapper.UrlMapper;
import com.laundrygo.shorturl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlSerivce {
    private final UrlMapper urlMapper;
    private final UrlRepository urlRepository;
    private final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String URL_POSTFIX = ".lg";
    private static final int SHORT_URL_LENGTH = 5;

    public List<UrlResDto> getAllUrls() {
        return urlRepository.findAll().stream()
                .map(urlMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public String getOriUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortUrl));
        url.setRequestCount(url.getRequestCount() + 1);
        return url.getOriginalUrl();
    }

    @Transactional
    public String createShortUrl(String originalUrl) {
        Url url = urlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> {
                    String shortUrl = generateUniqueShortUrl();
                    Url newUrl = new Url();
                    newUrl.setOriginalUrl(originalUrl);
                    newUrl.setShortUrl(shortUrl);
                    return urlRepository.save(newUrl);
                });
        return url.getShortUrl();
    }

    private String generateUniqueShortUrl() {
        String shortUrl;
        do {
            shortUrl = generateRandomShortUrl();
        } while (urlRepository.findByShortUrl(shortUrl).isPresent());
        return shortUrl;
    }

    private String generateRandomShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortUrl.append(URL_POSTFIX).toString();
    }
}
