package com.application.urlshortener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootTest
class UrlshortenerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void Testencode(){
		// Input string to be encoded
		String input = "Hello, World!";

		try {
			// Create a MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert the byte array to a hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			System.out.println(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
