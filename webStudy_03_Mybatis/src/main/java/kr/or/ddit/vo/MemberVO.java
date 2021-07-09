package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *	회원관리를 위한 Domain Layer 
 *	MyBatis (DataMapper, ORM) 을 이용한 테이블 조인 방법
 * 	ex) 회원 정보 상세 조회시 구매 상품 목록 동시 조회
 * 	1.	메인 테이블(MEMBER)을 기준으로 조인할 대상이 되는 테이블과의 관계파악.
 * 		MEMBER(1) : PROD(N)
 * 	2. 	각 테이블의 스키마 구조에 따라 VO 설계
 * 		MemberVO, ProdVO
 * 	3. 	VO 사이에 테이블간의 관계성 반영
 * 		1 : N - has many 관계
 * 		ex) MemberVO has many ProdVO
 * 		1 : 1 -	has a 관계
 * 		ex) ProdVO has a BuyerVO
 * 	4.	resultType  대신 resultMap 으로 수동 바인딩 설정
 * 		1 : N - has many -> collection
 * 		1 : 1 - has a -> assocation
 */
@Data
@EqualsAndHashCode(of = "memId")
@ToString(exclude= {"memPass", "memRegon1","memRegon2"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable{

	public MemberVO() {
	}
	
	
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}


	private String memId;
	private String memPass;
	private String memName;
	private String memRegno1;
	private String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private Boolean memDelete;
	
	private Set<ProdVO> prodList; // has many 관계 - 1:n 관계
	
	public String getMemTest() {
		return "테스트 데이터";
	}
}
