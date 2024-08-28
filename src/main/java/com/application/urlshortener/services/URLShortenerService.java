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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class URLShortenerService {

    private final URLInfoRepository urlInfoRepository;



    public ResponseEntity<Object> shortenUrl(URLShortenRequestVo urlShortenRequestVo){
        String shortURL = extractShortURL();
        URLInfoEntity urlInfoEntity = URLInfoEntity.builder().shortURL(shortURL).encodedURL(urlShortenRequestVo.getUrl()).expiryDate(getDefaultTimestamp()).build();
        urlInfoRepository.save(urlInfoEntity);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("url",shortURL));
    }

    public  ResponseEntity<Object> getOriginalUrl(String shortURL){
        String decodedURL = urlInfoRepository.findEncodedURLByShortURL(shortURL);
        log.info("Decoded URL: {}",decodedURL);
        return ResponseEntity.status(302).location(URI.create(decodedURL)).build();
    }

    private String extractShortURL(){
        String shortURL=EncodeUtils.generateRandomString();
        while(urlInfoRepository.existsById(shortURL)){
            shortURL = EncodeUtils.generateRandomString();
        }
        return shortURL;
    }

    public ResponseEntity<Object> getAllURLDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(urlInfoRepository.findAll());
    }

    public Timestamp getDefaultTimestamp(){
        Calendar calendar = Calendar.getInstance();

        // Add 2 days to the current time
        calendar.add(Calendar.DAY_OF_MONTH, 2);

        // Convert the calendar's time to a Timestamp
        return new Timestamp(calendar.getTimeInMillis());

    }
}
