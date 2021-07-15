package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

public interface BuyerDAO {

	/**
	 * 거래처 레코드 조회
	 * @return
	 */
	public int buyerTotalRecord(pagingVO<BuyerVO> paging);
	
	/**
	 * 거래처 목록 조회 및 검색
	 * @param paging
	 * @return 거래처 목록 call by reference
	 */
	public List<BuyerVO> selectBuyerList(pagingVO<BuyerVO> paging);
	
	/**
	 * 거래처 상세조회
	 * @param buyerId
	 * @return  buyer, {@link DataNotFoundException}발생
	 */
	public BuyerVO selectBuyer(String buyerId);
	
	/**
	 * 거래처 등록
	 * @param buyer
	 * @return OK , FALE
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return OK, FAIL, {@link DataNotFoundException}발생
	 */
	public int updateBuyer(BuyerVO buyer);
	

}











