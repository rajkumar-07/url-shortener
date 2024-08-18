package com.application.urlshortener.dao;

import com.application.urlshortener.entity.URLInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface URLInfoRepository extends JpaRepository<URLInfoEntity,String> {

    @Query(value = "SELECT encoded_url FROM url_details WHERE short_url = ?1", nativeQuery = true)
    String findEncodedURLByShortURL(String shortURL);
}
