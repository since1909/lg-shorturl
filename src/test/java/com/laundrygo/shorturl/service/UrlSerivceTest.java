package com.laundrygo.shorturl.service;

import com.laundrygo.shorturl.entity.Url;
import com.laundrygo.shorturl.exception.UrlNotFoundException;
import com.laundrygo.shorturl.mapper.UrlMapper;
import com.laundrygo.shorturl.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UrlSerivceTest {

    @Mock
    private UrlRepository urlRepository;
    @Mock
    private UrlMapper urlMapper;

    @InjectMocks
    private UrlSerivce urlSerivce;

    private Url url;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        url = new Url(123L, "www.example.com", "jlhyI.lg", 0);
    }

    @Test
    @DisplayName("원본 URL 조회 성공 테스트")
    void getOriUrl() {
        // given
        String shortUrl = "jlhyI.lg";
        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.of(url));

        // when
        String originalUrl = urlSerivce.getOriUrl(shortUrl);

        // then
        assertEquals("www.example.com", originalUrl);
        assertEquals(1, url.getRequestCount());
    }

    @Test
    @DisplayName("원본 URL 조회 실패 테스트")
    public void getOriUrlNotFound() {
        // given
        String shortUrl = "notfound.lg";
        when(urlRepository.findByShortUrl(shortUrl)).thenReturn(Optional.empty());

        // when & then
        UrlNotFoundException exception = assertThrows(UrlNotFoundException.class, () -> {
            urlSerivce.getOriUrl(shortUrl);
        });

        assertEquals("Short URL not found: notfound.lg", exception.getMessage());
    }

    @Test
    @DisplayName("단축 URL 생성 성공 테스트")
    public void createShortUrl() {
        // given
        String originalUrl = "www.new-url.com";
        when(urlRepository.findByOriginalUrl(originalUrl)).thenReturn(Optional.empty());

        String shortUrl = "XXXXX.lg";
        when(urlRepository.save(any(Url.class))).thenReturn(new Url(124L, originalUrl, shortUrl, 0));

        // when
        String result = urlSerivce.createShortUrl(originalUrl);

        // then
        assertEquals("XXXXX.lg", result);
    }

    @Test
    @DisplayName("단축 URL 생성 성공 테스트: 중복 생성")
    public void createExistShortUrl() {
        // given
        String originalUrl = "www.existing-url.com";
        String existingShortUrl = "QWERT.lg";
        when(urlRepository.findByOriginalUrl(originalUrl))
                .thenReturn(Optional.of(new Url(124L, originalUrl, existingShortUrl, 0)));

        // when
        String result = urlSerivce.createShortUrl(originalUrl);

        // then
        assertEquals(existingShortUrl, result);
        verify(urlRepository, never()).save(any(Url.class)); // 저장하지 않아야함
    }
}