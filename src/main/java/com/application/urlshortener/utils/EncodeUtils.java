package com.application.urlshortener.utils;

import java.util.Base64;
import java.util.UUID;

public class EncodeUtils {

    public static String encodeBase62(String data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data.getBytes());
    }

    public static String decodeBase62(String encodedData) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedData);
        return new String(decodedBytes);
    }

    public static String generateRandomString() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        return uuidStr.substring(0, 7);
    }
}
