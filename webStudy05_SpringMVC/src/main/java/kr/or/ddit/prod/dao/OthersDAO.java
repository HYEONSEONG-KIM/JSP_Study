package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품분류와 거래처를 선택할 수 있는 UI 구성에 사용할 Persistence Layer
 * 
 * @author PC
 *
 */
@Mapper
public interface OthersDAO {
	
	/**
	 * 분류코드와 분류명으로 구성
	 * @return
	 */
	public List<Map<String, Object>> selectLprodList();
	
	/**
	 * 거래처 코드와 거래처명, 거래처 분류로 구성
	 * @return
	 */
	public List<BuyerVO> selectBuyerList();


}
