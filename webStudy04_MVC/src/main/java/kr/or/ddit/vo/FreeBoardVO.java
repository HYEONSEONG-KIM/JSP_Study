package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.multipart.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boNo")
public class FreeBoardVO implements Serializable{
	
	private Integer boNo;
	private String boTitle;
	private String boWriter;
	private String boIp;
	private String boMail;
	private transient String boPass;
	private String boContent;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	
	//클라이언트로부터 받은 파일을 받기위해
	private MultipartFile[] boFiles;
	
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles == null) return;
		
		this.boFiles = boFiles;
		
		this.attatchList = new ArrayList<>(boFiles.length);
		for(MultipartFile file : boFiles) {
			if(file.isEmpty()) continue;
			this.attatchList.add(new AttatchVO(file));
		}
	}

	private List<AttatchVO> attatchList;
	
	// 파일을 수정시 변경하고자 하는 파일을 삭제하기위해 삭제 파일 정보
	// beanutils를 사용하기 위해 int형으로 
	private int[] delAttrNos;
	
	private List<FreeReplyVO> replyList;
}
