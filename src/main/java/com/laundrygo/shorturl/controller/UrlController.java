package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.entity.Url;
import com.laundrygo.shorturl.service.UrlSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlSerivce urlSerivce;

    @PostMapping
    public ResponseEntity<String> createShortUrl(@RequestBody String originalUrl) {
        Url url = urlSerivce.createShortUrl(originalUrl);
        return ResponseEntity.ok(url.getShortUrl());
    }
}
