package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.entity.Url;
import com.laundrygo.shorturl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UrlSerivce {
    private final UrlRepository urlRepository;
    private final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String URL_POSTFIX = ".lg";
    private static final int SHORT_URL_LENGTH = 5;

    public Url createShortUrl(String originalUrl) {
        return urlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> {
                    String shortUrl = generateUniqueShortUrl();
                    Url url = new Url();
                    url.setOriginalUrl(originalUrl);
                    url.setShortUrl(shortUrl);
                    return urlRepository.save(url);
                });
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
        return shortUrl.toString() + URL_POSTFIX;
    }
}
