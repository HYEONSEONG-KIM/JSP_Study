package kr.or.ddit.board.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(of="boNo")
@ToString(exclude = "boPass")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private int rnum;
	@NotNull(groups = UpdateGroup.class)
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
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	
	private MultipartFile[] boFiles;
	
	private List<AttatchVO> attatchList;
	
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles == null) return;
		
		this.boFiles = boFiles;
		
		attatchList = new ArrayList<>();
		for(MultipartFile file : boFiles) {
			if(file.isEmpty()) continue;
			attatchList.add(new AttatchVO(file));
		}
	}
	
}








