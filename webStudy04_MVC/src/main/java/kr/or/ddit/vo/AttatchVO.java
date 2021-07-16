package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.multipart.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@NoArgsConstructor
@ToString(exclude = "attFile")
public class AttatchVO implements Serializable{
	
	private MultipartFile attFile;

	public AttatchVO(MultipartFile attFile) {
		super();
		this.attFile = attFile;
		this.attFilename = attFile.getOriginalFilename();
		this.attMime = attFile.getContentType();
		this.attFilesize = attFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
		this.attSavename = UUID.randomUUID().toString();
	}
	
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private long attFilesize;
	private String attFancysize;
	private int attDownload;
}
