package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(of="boNo")
public class FreeBoardVO implements Serializable{
	
	@NotNull(groups= UpdateGroup.class)
	@Min(value = 1, groups = UpdateGroup.class)
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	@Email
	private String boMail;
	@NotBlank
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
	
	private int startAttNo;
	
}












