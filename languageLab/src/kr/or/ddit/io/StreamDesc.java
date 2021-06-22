package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 *	Stream : 연속성을 가진 일련의 데이터 집합이면서 동시에 전송 (단방향)통로
 *	
 *	스트림의 종류
 *	1. 데이터의 전송 크기에 따른 분류
 *		1) byte stream : xxxInputStream/xxxOutputStream
 *		2) char stream : xxxReader/xxxWriter
 *
 *	2. 스트림의 생성 방법 및 필러링 여부
 *		1) 1차 스트림 : 매체를 대상으로 직접적으로 생성 가능한 스트림
 *			- FileInputStream(file)
 *			- SocketInputStream => soceck.getInputStream()
 *		2) 2차(연결형) 스트림 : 1차 스트림을 대상으로 생성하는 스트림 
 *			- BufferedReader, BufferedWriter
 *			- DataInputStream,DataOutputStream 
 *			- ObjectInputStream(객체가 직렬화 가능해야 함), ObjectOutputStream
 *
 *	3. 스트림 사용 단계
 *		1) 매체(media)를 어플리케이션 내에서 제어할 수 있도록 객체화(ex.File객체 생성)
 *		2) 매체를 대상으로 1차 스트림 생성
 *		3) 데이터를 필터링 할 수 있는 2차 스트림 사용(optional)
 *		4) 기록이나 읽어들이는 작업 반복(EOF => -1 or null)
 *		5) 자원의 해제(*****) => finally, try~with~resource
 *
 */
public class StreamDesc {
	
	public static void main(String[] args) throws Exception {
		
		URL imageURL = new URL("https://www.google.com/logos/doodles/2021/get-vaccinated-wear-a-mask-save-lives-june-22-6753651837109280-law.gif");
		File saveFile = new File("c:/contents/google.gif");
		
		byte[] buffer = new byte[1024];
		int pointer = -1;
		try(
			InputStream is = imageURL.openStream();
			FileOutputStream fos = new FileOutputStream(saveFile);
		){
			while((pointer = is.read(buffer)) != -1) {
				fos.write(buffer, 0, pointer);
			}
			fos.flush();
		}
		
	}
	
	
	private static void readFileSystemResource() throws IOException {
		File readFile = new File("c:/contents/another day.txt");
		System.out.println(readFile.length());
		
		try(
				FileInputStream fis = new FileInputStream(readFile);
		){
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1) {
				System.out.write(buffer, 0, pointer);
			}
		}
	}
	
	private static void readClassPathResource() throws IOException{
		URL url = StreamDesc.class.getResource("/kr/or/ddit/io/오래된 노래.txt");
		String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
		System.out.println(filePath);
		if(url != null) {
			File readFile = new File(filePath);
			try(
//				FileReader reader = new FileReader();
				FileInputStream fis = new FileInputStream(readFile);
				InputStreamReader reader = new InputStreamReader(fis, "MS949");
				BufferedReader br = new BufferedReader(reader);
			){
				String tmp = null;
				while((tmp = br.readLine()) != null) {
					System.out.println(tmp);
				}
			}
		}
	}
	
	private static void serialize() throws Exception{
		// 객체의 (역)직렬화
				TestVO vo = new TestVO(23, "text");
				vo.setRegno1("232365");
				File writeFile = new File("c:/contents/test.dat");
				/* //객체 출력
				try(
					FileOutputStream fos = new FileOutputStream(writeFile);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
				){
					oos.writeObject(vo);
				}*/
				try(
					FileInputStream fis = new FileInputStream(writeFile);
					ObjectInputStream ois = new ObjectInputStream(fis);
				){
					//deserialization
					TestVO readData = (TestVO) ois.readObject();
					System.out.println(readData);
				}
	}
	
	
}



















