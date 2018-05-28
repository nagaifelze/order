package com.ibm.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("item")
@JsonPropertyOrder({ "partNumber", "quantity", "result",  "errormessage"})
public class ItemResult extends Item {
	String result;
	public ItemResult(){}
	public ItemResult(Item item) {
		super(item.getId(), item.getPartNumber(), item.getQuantity());
	}
	
	@JsonProperty("errormessage")
	String errorMessage;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
