package kr.or.ddit.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *	단방향 암호화(해시 함수) : 평문 복호화가 불가능한 방식
 *		SHA-512(암호화의 강도) 다양한 입력 데이터가 일정 갯수의 해시 문자(512비트)로 만들어짐
 *		=> MessageDigest
 *	양방향 암호화 : 평문 복호화가 가능 => Cipher
 *		1) 대칭키 방식(비밀키 방식) : 하나의 비밀키에 의해 암복호화 수행 => AES
 *		2) 비대칭키 방식(공개키 방식) : 한쌍(개인키, 공개키)의 키로 암복호화 복고화 수행 => RSA
 *
 */
public class EncryptingDesc {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String plain = "java";
		String encode = sha512Encrypting(plain);
		System.out.println(encode);
	}
	

	
	public static String sha512Encrypting(String plain) {
	
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encrypted = md.digest(input);
			
			String encoded = Base64.getEncoder().encodeToString(encrypted);

			String saved = "Tis88jp/niXFrDi36m/tbPMEqVlG+oRNOkCQX6t/FGoBf3TZuEuUhyvUzS6A4vEOlKv6azW5RgW0EgpymU9xEg==";
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void aesSample(String plain) {
		
		try {
			// 암호화 - 인코딩 
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey key = keyGen.generateKey();
			String ivText = "asdfgadq";
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] iv = md.digest(ivText.getBytes());
			
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			byte[] input = plain.getBytes();
			byte[] encrypted = cipher.doFinal(input);
			
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			
			System.out.println(encoded);
			
			byte[] decoded = Base64.getDecoder().decode(encoded);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decrypted = cipher.doFinal(decoded);
			System.out.println(new String(decrypted));
			
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rsaSample(String plain) {
	
		
		try {
			KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
			pairGen.initialize(2048);
			KeyPair pair = pairGen.generateKeyPair();
			PrivateKey privatekey = pair.getPrivate();
			
			PublicKey publicKey = pair.getPublic();
			Cipher cipher = Cipher.getInstance("RSA");

			cipher.init(Cipher.ENCRYPT_MODE, privatekey);
			byte[] input = plain.getBytes();
			byte[] encrypted = cipher.doFinal(input);
			
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			
			System.out.println(encoded);
			
			byte[] decoded = Base64.getDecoder().decode(encoded);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] decrypted = cipher.doFinal(decoded);
			
			System.out.println(new String(decrypted));
			
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}

