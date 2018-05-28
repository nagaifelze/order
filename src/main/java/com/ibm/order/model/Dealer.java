package com.ibm.order.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Dealer {
	@NotNull
	@Column(name="dealer_id")
	@JsonProperty("dealerid")
	String dealerId;
	
	@NotNull
	@Column(name="dealer_access_key")
	@JsonProperty("dealeraccesskey")
	String dealerAccessKey;
	
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerAccessKey() {
		return dealerAccessKey;
	}
	public void setDealerAccessKey(String dealerAccessKey) {
		this.dealerAccessKey = dealerAccessKey;
	}
}
