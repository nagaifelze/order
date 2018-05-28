package com.ibm.order.model;

public enum ErrorInfo{
	Not_Authorized("Not authorized", ""),
	Invalid_Order_Delivery_Address("Invalid order", "Invalid delivery address"),
	Invalid_Order_Item_List("Invalid order", "Invalid order item list"),
	Invalid_Order("Invalid order", ""),
	Invalid_Order_Item_Entry("Invalid order", "Invalid order item entry");
	String name;
	String errorMsg;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	ErrorInfo(String pName, String pErrorMsg){
		this.name = pName;
		this.errorMsg = pErrorMsg;
	}
}
