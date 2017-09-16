package com.fooco.FoodTruc.foodTruck.util;

// ����¡ ó�� ���� Ŭ���� (���� ��밡��)
public class PagingUtil {
	public int totalRecordCount;	// ��ü ���ڵ�� ī��Ʈ
	public int recordsPerPage;		// ������ �� ���ڵ� ��
	public int totalPage;			// ��ü ��������
	public int currentPage;			// ���� ������ ��ȣ
	public int firstPageNo;			// ù��° ������ ��ȣ
	public int prevPageNo;			// ���� ������ ��ȣ
	public int startPageNo;			// ���� ������ (����¡ �ʺ� ����)
	public int endPageNo;           // �� ������ (����¡ �ʺ� ����)
	public int nextPageNo;          // ���� ������ ��ȣ
	public int finalPageNo;         // ������ ������ ��ȣ
	public int sizeOfPage;          // �������� ������ ���� (1,2,3,4,5 ����)

	public PagingUtil(int currentPage, int recordsPerPage) {
		// ���� ������ ��ȣ�� ������ �� ������ ���ڵ� �� ����.
		this.currentPage = currentPage;	//���� ������ ��ȣ ����.
		this.sizeOfPage = 5;	// ����Ʈ�� ��������ȣ�� 5���� ������ (1,2,3,4,5)
		
		//recordsPerPage�� 0�� �ƴϸ� recordsPerPage, 0�̸� ������ 16(default : 16)
        this.recordsPerPage = (recordsPerPage != 0) ? recordsPerPage : 16;
        System.out.println("===============================");
        System.out.println("		PagingUtilŬ���� Debug		");
        System.out.println("debug > �������� ������ ���� : " + this.sizeOfPage);
        System.out.println("debug > ������ �� ���ڽ� �� : " + this.recordsPerPage);
        System.out.println("debug > ���� ������ ��ȣ : " + this.currentPage);
        System.out.println("===============================");
	}
	
	// ������ ���� 
	public void makePaging(){
		if(totalRecordCount == 0) return;	//�Խñ� ��ü ���� ���� ���	
		
		if (currentPage == 0) setCurrentPage(1);        // �⺻ �� ����
        
        if (recordsPerPage == 0) setRecordsPerPage(16);        // �⺻ �� ����
        
        //������ ������
        int finalPage = (totalRecordCount + (recordsPerPage - 1)) / recordsPerPage;
        
        if (currentPage > finalPage)
            setCurrentPage(finalPage);// �⺻ �� ����
        
        if (currentPage < 0 || currentPage > finalPage)
        	currentPage = 1;            // ���� ������ ��ȿ�� üũ
        
        // ���� ������ (��ü)
        boolean isNowFirst = currentPage == 1 ? true : false;
        boolean isNowFinal = currentPage == finalPage ? true : false;
        
        int startPage = ((currentPage - 1) / sizeOfPage) * sizeOfPage + 1;
        int endPage = startPage + sizeOfPage - 1;        
        
        if (endPage > finalPage)
            endPage = finalPage;	//���� �������������� finalPage(������ ������) ���� ũ�� endPage�� �������������� ����.
        
        setFirstPageNo(1); // ù��° ������ ��ȣ
        
        if (isNowFirst)
            setPrevPageNo(1);   // ���� ������ ��ȣ
        else    // ���� ������ ��ȣ
            setPrevPageNo(((currentPage - 1) < 1 ? 1 : (currentPage - 1)));
 
        setStartPageNo(startPage);  // ����������
        setEndPageNo(endPage); // �� ������
        
        if (isNowFinal)
            setNextPageNo(finalPage); // ���� ������ ��ȣ
        else
            setNextPageNo(((currentPage + 1) > finalPage ? finalPage : (currentPage + 1)));
        
        setFinalPageNo(finalPage);  // ������ ������ ��ȣ

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
