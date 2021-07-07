package kr.or.ddit.prod.dao;

import kr.or.ddit.vo.ProdVO;

public interface ProdDAO {
	/**
	 * 상품 상세 조회(구매자 목록 동시 조회)
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
}
