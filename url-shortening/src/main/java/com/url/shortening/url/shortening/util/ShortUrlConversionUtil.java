package com.url.shortening.url.shortening.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class ShortUrlConversionUtil {

	private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	// Method to generate MD5 hash of a URL
	private String generateMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return String.format("%032x", number); // Convert to 32-character hexadecimal string
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not found", e);
		}
	}

	// Method to get the first 6 bytes of the MD5 hash and convert to decimal
	public static long getFirst6BytesAsDecimal(String md5Hash) {
		// Extract the first 6 bytes (12 characters in hex)
		String first6BytesHex = md5Hash.substring(0, 12);
		// Convert to decimal
		return new BigInteger(first6BytesHex, 16).longValue();
	}

	// Method to encode a decimal number into Base62
	public static String encodeBase62(long number) {
		StringBuilder encoded = new StringBuilder();
		while (number > 0) {
			int remainder = (int) (number % 62);
			encoded.append(BASE62.charAt(remainder));
			number /= 62;
		}
		return encoded.reverse().toString(); // Reverse to get the correct order
	}

	// Method to shorten a URL
	public String shortenURL(String longURL) {
		// Step 1: Generate MD5 hash of the long URL
		String md5Hash = generateMD5(longURL);

		// Step 2: Get the first 6 bytes of the MD5 hash as a decimal number
		long decimalValue = getFirst6BytesAsDecimal(md5Hash);

		// Step 3: Encode the decimal number into Base62
		return encodeBase62(decimalValue);
	}
}
