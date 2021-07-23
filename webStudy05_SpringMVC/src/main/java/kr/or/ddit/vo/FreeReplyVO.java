package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="repNo")
public class FreeReplyVO implements Serializable{

	private Integer repNo;
	private Integer boNo;
	private String repContent;
	private String repWriter;
	private String repMail;
	private String repPass;
	private String preDate;
	private Integer repParent;
}
