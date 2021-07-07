package kr.or.ddit.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 *	encoding(부호화)/ decoding(복호화) : 데이터의 전송이나 저장시에 해당 매체가 인식할 수 있는 데이터 표현방식으로 표현을 변화하는 과정
 *	URL encoding(Percent encording), Base64 encoding
 *	encrypting(암호화) : 허가되지 않은 사용자로부터 데이터를 보호할 목적으로 변환하는 과정
 *
 * => 보호하려고 하느냐 공개하려고 하느냐의 차이
 * 
 *
 */
public class EncodingDesc {
	public static void main(String[] args) {
		String plain = "아무노래나 일단 틀어";
		try {
			String encoded = URLEncoder.encode(plain, "UTF-8");
			System.out.println(encoded);
			
			String decoded = URLDecoder.decode(encoded, "UTF-8");
			System.out.println(decoded);
			
			String resPath = "C:/contents/test6.jpg";
			File file = new File(resPath);
			// 메모리에 저장하기에 생성자 존재
			// file -> buffer로 이동
			try(
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				FileInputStream fis = new FileInputStream(file);
			){
				byte[] buffer = new byte[1024];
				int pointer = -1;
				while((pointer = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, pointer);
				}
				byte[] readData = baos.toByteArray();
				System.out.println(readData.length);
				
				// 바이트를 문자열로 인코딩
				encoded = Base64.getEncoder().encodeToString(readData);
//				System.out.println(encoded);
				
				// 문자열은 바이트로 디코딩
				byte[] decodedArray = Base64.getDecoder().decode(encoded);
				System.out.println(decodedArray.length);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}








