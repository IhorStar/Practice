package ua.nure.starodubets.Practice3.part4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class generates a hash value for an input String
 *
 * @author Ihor Starodubets
 *
 */
public class Part4 {

	/**
	 * The method generates a hash value for an input String
	 *
	 * @param input the String from which you want to get hash
	 * @param algorithm the name of the algorithm requested
	 * @return String in hex format
	 * @throws NoSuchAlgorithmException if a particular cryptographic algorithm is requested but is not available in the environment
	 */
	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		messageDigest.update(input.getBytes());
		byte[] byteData = messageDigest.digest();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < byteData.length; i++) {
			builder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return builder.toString();
	}

	/**
	 * The method is entry point into the program
	 *
	 * @param args String array
	 * @throws NoSuchAlgorithmException if a particular cryptographic algorithm is requested but is not available in the environment
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-256"));
	}
}
