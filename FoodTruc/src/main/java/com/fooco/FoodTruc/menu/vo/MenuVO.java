package com.fooco.FoodTruc.menu.vo;

public class MenuVO {
	//  �޴��� �ĺ��ڵ�
    private int menuCode;
    //  �ش� Ʈ���� �޴��� �ĺ��ڵ�
    private int truckNum;
    //  �޴� ī�װ�(�з�)
    private String menuCategory;
    //  �޴��̸�
    private String menuName;
    //  �޴�����
    private int menuPrice;
    //  ������
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
