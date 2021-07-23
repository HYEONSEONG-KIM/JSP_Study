package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

/**
 *	상품 관리(CRUD) Persistence Layer 
 *
 */
@Mapper
public interface ProdDAO {
	
	/**
	 * 신규 상품 등록
	 * @param prod pk를 제외한 나머지 상품 데이터를 가진 VO
	 * @param SqlSession TODO
	 * @return rowcnt > 0 성공, PK는 call by reference로 확인
	 */
	public int insertProd(ProdVO prod);
	
	/**
	 * 토탈 레코드 조회
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(pagingVO<ProdVO> paging);
	
	/**
	 * 상품 목록 조회
	 * @param paging
	 * @return
	 */
	public List<ProdVO> selectProdList(pagingVO<ProdVO> paging);
	
	
	/**
	 * 상품 상세 조회(구매자 목록 동시 조회)
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	
	/**
	 * 상품 정보 수정
	 * @param prod
	 * @param sqlSession TODO
	 * @return
	 */
	public int updateProd(ProdVO prod);
	
	
}
