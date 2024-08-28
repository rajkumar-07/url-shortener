package com.application.urlshortener.controller;

import com.application.urlshortener.services.URLShortenerService;
import com.application.urlshortener.vo.URLShortenRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin("*")
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;

    @PostMapping("/shorten-url")
    public ResponseEntity<Object> shortenUrl(@RequestBody URLShortenRequestVo urlShortenRequestVo){
        return urlShortenerService.shortenUrl(urlShortenRequestVo);
    }

    @GetMapping("/{shotURL}")
    public ResponseEntity<Object> getOriginalUrl(@PathVariable("shotURL") String shortURL){
        return urlShortenerService.getOriginalUrl(shortURL);
    }

    @GetMapping
    public ResponseEntity<Object> getAllURLDetails(){
        return urlShortenerService.getAllURLDetails();
    }
}
