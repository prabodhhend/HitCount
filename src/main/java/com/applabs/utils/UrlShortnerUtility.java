/**
 * 
 */
package com.applabs.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * @author prabodh.hend
 *
 */
@Component
public class UrlShortnerUtility {

	public String shortenUrl(String url) {

		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));
		
		return "";
	}

}
