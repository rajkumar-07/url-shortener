package com.application.urlshortener.services;

import com.application.urlshortener.dao.URLInfoRepository;
import com.application.urlshortener.entity.URLInfoEntity;
import com.application.urlshortener.utils.EncodeUtils;
import com.application.urlshortener.vo.URLShortenRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class URLShortenerService {

    private final URLInfoRepository urlInfoRepository;

    public ResponseEntity<Object> shortenUrl(URLShortenRequestVo urlShortenRequestVo){
        String encodedURL = EncodeUtils.encodeBase62(urlShortenRequestVo.getUrl());
        String shortURL = extractShortURL(encodedURL);
        URLInfoEntity urlInfoEntity = URLInfoEntity.builder().shortURL(shortURL).encodedURL(encodedURL).build();
        urlInfoRepository.save(urlInfoEntity);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("url",shortURL));
    }

    public  ResponseEntity<Object> getOriginalUrl(String shortURL){

        String decodedURL = EncodeUtils.decodeBase62(urlInfoRepository.findEncodedURLByShortURL(shortURL));
        log.info("Decoded URL: {}",decodedURL);
        return ResponseEntity.status(302).location(URI.create(decodedURL)).build();
    }

    private String extractShortURL(String encodedURL){
        String shortURL  = encodedURL.substring(0,7);
        while(urlInfoRepository.existsById(shortURL)){
            shortURL = EncodeUtils.generateRandomString();
        }
        return shortURL;
    }
}
