package com.ibm.order.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonRootName("order")
public class OrderResponse {
	@JsonProperty(required=false)
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	String result;
	
    @JsonInclude(JsonInclude.Include.NON_NULL) 
	@JsonProperty(required=false)
	String error;
	
    @JsonInclude(JsonInclude.Include.NON_NULL) 
	@JsonProperty(value="errormessage", required=false)
	String errorMessage;
	
	@JsonProperty(required=false)
	@JsonSerialize( contentAs = ItemResult.class )
	@JacksonXmlElementWrapper( localName = "orderitems" )
	@JacksonXmlProperty( localName = "item")
	List<ItemResult> orderItems;

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

	public List<ItemResult> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<ItemResult> orderItems) {
		this.orderItems = orderItems;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}