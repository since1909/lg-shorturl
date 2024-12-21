package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.entity.Url;
import com.laundrygo.shorturl.service.UrlSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlSerivce urlSerivce;

    /**
     * 전체 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getUrls() {
        return ResponseEntity.ok(urlSerivce.getAllUrls());
    }

    /**
     * original url 조회
     *
     * @param shortUrl
     * @return
     */
    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> getOriUrl(@PathVariable String shortUrl) {
        return ResponseEntity.ok(urlSerivce.getOriUrl(shortUrl));
    }

    /**
     * short url 생성
     *
     * @param originalUrl
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createShortUrl(@RequestBody String originalUrl) {
        return ResponseEntity.ok(urlSerivce.createShortUrl(originalUrl));
    }


}
