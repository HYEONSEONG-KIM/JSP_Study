package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// target에 해당하는 interface
// 하나의 part를 담기 위함
public interface MultipartFile {
	
	
	/**
	 * 파일이름 구하는 메서드
	 * @return
	 */
	public String getOriginalFilename();
	/**
	 * part객체가 비어있는지 확인하는 메서드
	 * @return
	 */
	public boolean isEmpty();
	/**
	 *	
	 *	partName, 즉 view에서 form에 담을때 input태그에 설정한 name 값
	 * @return
	 */
	public String getName();
	/**
	 * mimeType 반환
	 * @return
	 */
	public String getContentType();
	/**
	 * part객체의 크기
	 * @return
	 */
	public long getSize();
	
	/**
	 *  1차 스트림을 이용하여 업로드한 파일 read
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException;
	/**
	 *  바이트 타입으로 변환
	 * @return
	 * @throws IOException
	 */
	public byte[] getBytes() throws IOException;
	
	/** 
	 * 한번에 파일 업로드
	 * 
	 * @param saveFile
	 * @throws IOException
	 */
	public void transferTo(File saveFile) throws IOException;
}
