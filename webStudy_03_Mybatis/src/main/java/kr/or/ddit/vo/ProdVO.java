package kr.or.ddit.vo;

import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	상품관리 Domain Layer 
 *
 */
@Data
@EqualsAndHashCode(of = "prodId")

public class ProdVO {

	private String prodId;
	private String prodName;
	private String prodLgu;
	private String lprodNm;
	private String prodBuyer;
	//****
	private BuyerVO buyer; // has a 관계 => 1 : 1 관계
	
	private List<MemberVO> memberList;
	
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
}
