package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

/**
 *	상품 관리(CRUD) Business Logic Layer(Service Layer) 
 * 
 *
 */
public interface ProdService {
	

	/**
	 * 상품 입력
	 * @param prod
	 * @return 성공시 call by reference로 PK조회 가능
	 */
	public ServiceResult createProd(ProdVO prod);

	/**
	 * 제품 목록 조회
	 * @param pagingVO -> call by reference로 dataList와 totalRecord 채움
	 */
	public void retrieveProdList(pagingVO<ProdVO> pagingVO);
	
	/**
	 * 제품 상세 조회
	 * @param prodId
	 * @return 존재하지 않으면 {@link DataNotFoundException}
	 */
	public ProdVO retrieveProd(String prodId);
	
	/**
	 * 
	 * @param prod
	 * @return 존재하지 않으면 {@link DataNotFoundException}, OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
}
