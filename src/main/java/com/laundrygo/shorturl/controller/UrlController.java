package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.service.UrlSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "URL Shortener", description = "URL 단축 및 조회 API")
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlSerivce urlSerivce;

    /**
     * 전체 조회
     *
     * @return
     */
    @GetMapping
    @Operation(summary = "전체 URL 조회", description = "URL 목록을 반환합니다.")
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
    @Operation(summary = "원본 URL 조회", description = "단축 URL의 원본 URL을 반환합니다.")
    public ResponseEntity<?> getOriUrl(
            @Parameter(name = "shortUrl", description = "단축 URL", example = "short.lg")
            @PathVariable String shortUrl) {
        return ResponseEntity.ok(urlSerivce.getOriUrl(shortUrl));
    }

    /**
     * short url 생성
     *
     * @param originalUrl
     * @return
     */
    @PostMapping
    @Operation(summary = "단축 URL 생성", description = "단축 URL을 생성합니다.")
    public ResponseEntity<?> createShortUrl(@RequestBody String originalUrl) {
        return ResponseEntity.ok(urlSerivce.createShortUrl(originalUrl));
    }


}
