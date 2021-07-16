package kr.or.ddit.buyer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.listener.ContextLoaderListener;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

public class buyerServiceImpl implements buyerService {

	private BuyerDAO dao = BuyerDAOImpl.getInstance();
	
	private buyerServiceImpl() {}
	
	private static buyerServiceImpl self = null;
	
	public static buyerServiceImpl getInstance() {
		if(self == null) {
			self = new buyerServiceImpl();
		}
		return self;
	}
	
	private void proccessBuyerImage(BuyerVO buyer) {
		MultipartFile file = buyer.getBuyerImage();

		File saveFolder = ContextLoaderListener.buyerImages;
		String savename = buyer.getBuyerImg();
		System.out.println(savename);
		File saveFile = new File(saveFolder, savename);
		try {
			file.transferTo(saveFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void retrieveBuyerList(pagingVO<BuyerVO> paging) {
		int record = dao.buyerTotalRecord(paging);
		paging.setTotalRecord(record);

		List<BuyerVO> buyerList = dao.selectBuyerList(paging);
		paging.setDataList(buyerList);
	}

	@Override
	public BuyerVO retrieceBuyer(String buyerId) {
		
		BuyerVO buyer = dao.selectBuyer(buyerId);
		
		if(buyer == null) {
			throw new DataNotFoundException();
		}else {
			
			return buyer;
		}
		
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		
		dao.insertBuyer(buyer);
		ServiceResult result = null;
		
		if(StringUtils.isBlank(buyer.getBuyerId())) {
			result =ServiceResult.FAIL;
		}else {
			result = ServiceResult.OK;
			proccessBuyerImage(buyer);
		}
		
		return result;
	}

	

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return null;
	}

}
