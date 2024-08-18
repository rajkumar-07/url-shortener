package com.application.urlshortener.vo;

import lombok.Data;

@Data
public class URLShortenRequestVo {
    private String url;
    private String shortUrl;
}
