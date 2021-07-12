package kr.or.ddit.vo;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	상품관리 Domain Layer 
 *
 */
@Data
@EqualsAndHashCode(of = "prodId")

public class ProdVO {
	
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String prodLgu;
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	//****
	private BuyerVO buyer; // has a 관계 => 1 : 1 관계
	
	private List<MemberVO> memberList;
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	@NotBlank
	private String prodImg;
	@NotNull
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
}
