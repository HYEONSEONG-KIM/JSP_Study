package kr.or.ddit.vo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.contraints.FileMime;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.coobird.thumbnailator.Thumbnails;

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
@ToString(exclude= {"memPass", "memRegon1","memRegon2", "memImg", "memImage"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable,HttpSessionBindingListener{

	public MemberVO() {
	}
	
	
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}

	// javax.으로 시작하는것으로 사용 -> 다른걸로 바뀌면 vo자체를 바꿔야함
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12,groups = {Default.class, DeleteGroup.class})
	private String memPass;
	@NotBlank()
	private String memName;
	@NotBlank(groups=InsertGroup.class)
	private String memRegno1;
	@NotBlank(groups=InsertGroup.class)
	private String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	//@TelNumber
	private String memHometel;
	//@TelNumber
	private String memComtel;
	@NotBlank
	//@TelNumber
	private String memHp;
	@NotBlank
	@Email
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
	
	private String memRole;
	private byte[] memImg;
	private String memPath;
	
	@FileMime(mime = "image/")
	private transient MultipartFile memImage;
	
	public String getBase64Img() {
		if(memImg == null) return null;
		
		return Base64.encodeBase64String(memImg);
	}
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage == null || memImage.isEmpty()) {
			return;
		}
		this.memImage = memImage;

		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Thumbnails.of(this.memImage.getInputStream())
		.size(100, 200).toOutputStream(os);
		
		this.memImg = os.toByteArray();
		this.memPath = UUID.randomUUID().toString();
	}

	

	@Override
	public void valueBound(HttpSessionBindingEvent event) {

		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			Map<String, MemberVO> userList = (Map<String, MemberVO>) application.getAttribute("currentUserList");
			
			userList.put(memId, this);
		}
	}



	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {

		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			Map<String, MemberVO> userList = (Map<String, MemberVO>) application.getAttribute("currentUserList");
			
			userList.remove(memId, this);
		}
	}
}
