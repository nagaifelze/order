package com.ibm.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name="dealer_item")
@JsonRootName("item")
public class Item {
	public Item() {
	}
	public Item(int pId, int pPartNumber, int pQuantity) {
		this.id = pId;
		this.partNumber = pPartNumber;
		this.quantity = pQuantity;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	int id;
	
	@Column(name="part_numner")
	@JsonProperty("partnumber")
	@NotNull
	int partNumber;
	
	@NotNull
	int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}