package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *	거래처 정보 Domain Layer 
 * 
 *
 */
@Data
@EqualsAndHashCode(of="buyerId")
@ToString
public class BuyerVO implements Serializable {
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank()
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private LprodVO lprod;
	private String buyerImg;
	
	private MultipartFile buyerImage;
	
	public void setBuyerImage(MultipartFile buyerImage) {
		this.buyerImage = buyerImage;
		this.buyerImg = UUID.randomUUID().toString();
	}
}
