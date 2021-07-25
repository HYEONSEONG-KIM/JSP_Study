package kr.or.ddit.board.vo;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *	페이징 처리에 관련된 속성의 집합 
 *
 */
@NoArgsConstructor
@Getter
public class PagingVO<T>{
	// DB의 레코드 갯수
	private int totalRecord;
	// 출력할 게시글 갯수
	private int screenSize = 10;
	// 한 화면에 표시될 페이지 수
	private int blockSize = 5;
	// 총 페이지
	private int totalPage;
	private int currentPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	

	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	
	
	private SearchVO simpleSearch; // 단순 키워드 검색

	public void setSimpleSearch(SearchVO simpleSearch) {
		this.simpleSearch = simpleSearch;
	}
	
	
	private T detailSearch; // 상세검색
	
	public void setDetailSearch(T detailSearch) {
		this.detailSearch = detailSearch;
	}
	
	
	
	private List<T> dataList;
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)(Math.ceil(totalRecord/(double)screenSize));
	}
	
	
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize -1);
		
		startPage = blockSize * ((currentPage - 1) / blockSize) + 1;
		endPage = startPage + (blockSize - 1);
	}
	
	
	private static final String LINKPTRN = "<a class = 'pageLink btn btn-secondary' href = '#' data-page= '%d'>%s</a>\n";
	
	
	public String getPagingHTML() {
//		<a href = "?page=1"></a>
		StringBuffer html = new StringBuffer();
		
		if(startPage > 1) {
			html.append(String.format(LINKPTRN, startPage-1, "◀"));
		}
		
		endPage = endPage > totalPage? totalPage : endPage;
		for(int page = startPage; page <= endPage; page++) {
			if(currentPage == page) {
				html.append("<span class = 'btn btn-dark'>" + page+"</span>");
			}else {
				html.append(String.format(LINKPTRN, page, page));
			}
		}
		if(endPage < totalPage) {
			html.append(String.format(LINKPTRN, endPage + 1, "▶"));
		}
		
		return html.toString();
	}
}

