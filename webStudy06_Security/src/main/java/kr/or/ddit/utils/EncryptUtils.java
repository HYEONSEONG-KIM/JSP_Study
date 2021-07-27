package kr.or.ddit.utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *	Sha-512방식을 이용한 해시 함수 유틸 
 * 
 *
 */
public class EncryptUtils {
	
	public static boolean matches(String plain, String saved) {
		
		String encoded = encryptSha512Base64(plain);
		return saved.equals(encoded);
	}
	
	
	public static String encryptSha512Base64(String plain) {
		
		byte[] encrypted = DigestUtils.sha512(plain);
		
		return Base64.encodeBase64String(encrypted);
		
	}
}
