package com.fooco.FoodTruc.foodTruck.util;

// 페이징 처리 위한 클래스 (공통 사용가능)
public class PagingUtil {
	public int totalRecordCount;	// 전체 레코드수 카운트
	public int recordsPerPage;		// 페이지 당 레코드 수
	public int totalPage;			// 전체 페이지수
	public int currentPage;			// 현재 페이지 번호
	public int firstPageNo;			// 첫번째 페이지 번호
	public int prevPageNo;			// 이전 페이지 번호
	public int startPageNo;			// 시작 페이지 (페이징 너비 기준)
	public int endPageNo;           // 끝 페이지 (페이징 너비 기준)
	public int nextPageNo;          // 다음 페이지 번호
	public int finalPageNo;         // 마지막 페이지 번호
	public int sizeOfPage;          // 보여지는 페이지 갯수 (1,2,3,4,5 갯수)

	public PagingUtil(int currentPage, int recordsPerPage) {
		// 현재 페이지 번호와 페이지 당 보여줄 레코드 수 셋팅.
		this.currentPage = currentPage;	//현재 페이지 번호 셋팅.
		this.sizeOfPage = 5;	// 디폴트로 페이지번호를 5개씩 보여줌 (1,2,3,4,5)
		
		//recordsPerPage가 0이 아니면 recordsPerPage, 0이면 무조건 16(default : 16)
        this.recordsPerPage = (recordsPerPage != 0) ? recordsPerPage : 16;
        System.out.println("===============================");
        System.out.println("		PagingUtil클래스 Debug		");
        System.out.println("debug > 보여지는 페이지 갯수 : " + this.sizeOfPage);
        System.out.println("debug > 페이지 당 레코스 수 : " + this.recordsPerPage);
        System.out.println("debug > 현재 페이지 번호 : " + this.currentPage);
        System.out.println("===============================");
	}
	
	// 페이지 생성 
	public void makePaging(){
		if(totalRecordCount == 0) return;	//게시글 전체 수가 없는 경우	
		
		if (currentPage == 0) setCurrentPage(1);        // 기본 값 설정
        
        if (recordsPerPage == 0) setRecordsPerPage(16);        // 기본 값 설정
        
        //마지막 페이지
        int finalPage = (totalRecordCount + (recordsPerPage - 1)) / recordsPerPage;
        
        if (currentPage > finalPage)
            setCurrentPage(finalPage);// 기본 값 설정
        
        if (currentPage < 0 || currentPage > finalPage)
        	currentPage = 1;            // 현재 페이지 유효성 체크
        
        // 시작 페이지 (전체)
        boolean isNowFirst = currentPage == 1 ? true : false;
        boolean isNowFinal = currentPage == finalPage ? true : false;
        
        int startPage = ((currentPage - 1) / sizeOfPage) * sizeOfPage + 1;
        int endPage = startPage + sizeOfPage - 1;        
        
        if (endPage > finalPage)
            endPage = finalPage;	//구한 마지막페이지가 finalPage(마지막 페이지) 보다 크면 endPage를 마지막페이지로 셋팅.
        
        setFirstPageNo(1); // 첫번째 페이지 번호
        
        if (isNowFirst)
            setPrevPageNo(1);   // 이전 페이지 번호
        else    // 이전 페이지 번호
            setPrevPageNo(((currentPage - 1) < 1 ? 1 : (currentPage - 1)));
 
        setStartPageNo(startPage);  // 시작페이지
        setEndPageNo(endPage); // 끝 페이지
        
        if (isNowFinal)
            setNextPageNo(finalPage); // 다음 페이지 번호
        else
            setNextPageNo(((currentPage + 1) > finalPage ? finalPage : (currentPage + 1)));
        
        setFinalPageNo(finalPage);  // 마지막 페이지 번호

	}
	
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	}

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getSizeOfPage() {
		return sizeOfPage;
	}

	public void setSizeOfPage(int sizeOfPage) {
		this.sizeOfPage = sizeOfPage;
	}
	
	
}
