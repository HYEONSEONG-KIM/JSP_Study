package kr.or.ddit.board.vo;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@ToString
public class AttatchVO {
	
	private MultipartFile attFile;
	@NotNull
	private Integer attNo;
	@NotNull(groups = InsertGroup.class)
	private Integer boNo;
	@NotBlank
	private String attFilename;
	@NotBlank
	private String attSavename;
	private String attMime;
	@NotBlank
	private long attFilesize;
	@NotBlank
	private String attFancysize;
	private Integer attDownload;
	
	public AttatchVO(MultipartFile attFile) {
		this.attFile = attFile;
		this.attFilename = attFile.getOriginalFilename();
		this.attSavename = UUID.randomUUID().toString();
		this.attMime = attFile.getContentType();
		this.attFilesize = attFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
	}
	
}












