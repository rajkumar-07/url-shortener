package com.application.urlshortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Builder
@Table(name = "url_details")
@AllArgsConstructor
@NoArgsConstructor
public class URLInfoEntity {

    @Id
    @Column(name="short_url")
    private String shortURL;

    @Column(name="encoded_url")
    private String encodedURL;

    @Column(name="expiry_date")
    private Timestamp expiryDate;
}
