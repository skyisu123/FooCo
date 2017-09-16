package com.fooco.FoodTruc.menu.vo;

public class MenuVO {
	//  메뉴를 식별코드
    private int menuCode;
    //  해당 트럭의 메뉴판 식별코드
    private int truckNum;
    //  메뉴 카테고리(분류)
    private String menuCategory;
    //  메뉴이름
    private String menuName;
    //  메뉴가격
    private int menuPrice;
    //  재고수량
    private int inventory;
	
    public MenuVO() {}
    
    public int getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	public int getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(int truckNum) {
		this.truckNum = truckNum;
	}
	public String getMenuCategory() {
		return menuCategory;
	}
	public void setMenuCategory(String menuCategory) {
		this.menuCategory = menuCategory;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
    
    
}
