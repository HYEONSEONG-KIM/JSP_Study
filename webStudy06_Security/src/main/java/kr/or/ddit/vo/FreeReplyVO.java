package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="repNo")
public class FreeReplyVO implements Serializable{

	@NotNull(groups = UpdateGroup.class)
	private Integer repNo;
	@NotNull
	private Integer boNo;
	private String repContent;
	@NotBlank
	private String repWriter;
	private String repMail;
	@NotBlank
	private String repPass;
	private String repDate;
	private Integer repParent;
}
