package kr.or.ddit.prod.service;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

public class ProdServiceImpl implements ProdService {

	private ProdDAO prodDAO = new ProdDAOImpl();
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		
		prodDAO.insertProd(prod);
		ServiceResult result = null;
		
		if(StringUtils.isBlank(prod.getProdId())){
			result = ServiceResult.FAIL;
		}else {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public void retrieveProdList(pagingVO<ProdVO> pagingVO) {
		
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		pagingVO.setDataList(prodDAO.selectProdList(pagingVO));
		
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		
		return prodDAO.selectProd(prodId);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
