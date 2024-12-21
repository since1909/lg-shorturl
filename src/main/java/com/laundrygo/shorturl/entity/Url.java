package com.laundrygo.shorturl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lg_url")
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", unique = true, nullable = false)
    private String originalUrl;

    @Column(name = "short_url", unique = true, nullable = false, length = 8)
    private String shortUrl;

    @Column(name = "request_count", nullable = false)
    private int requestCount = 0;
}
