package kr.or.ddit.buyer.service;

import java.util.List;


import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

public interface buyerService {
	
	
	
	
	
	
	/**
	 * 거래처 목록 조회
	 * @param paging
	 * 
	 */
	public void retrieveBuyerList(pagingVO<BuyerVO> paging);
	
	/**
	 * 거래처 상세 조회
	 * @param buyerId
	 * @return
	 */
	public BuyerVO retrieceBuyer(String buyerId);
	
	
	/**
	 * 거래처 등록
	 * @param buyer
	 * @return OK, FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return OK, FAIL, {@link DataNotFoundException} 발생
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
}














