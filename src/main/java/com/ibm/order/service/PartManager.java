package com.ibm.order.service;

import java.util.Arrays;
import java.util.List;

import com.ibm.order.model.DeliveryAddress;

public interface PartManager {
	public static final List<Integer> VALID_PART_NUMBERS = Arrays.asList(1234, 3245, 4567, 2938, 2111, 2110, 2101);
	public static final List<Integer> OUT_OF_STOCK_PART_NUMBERS = Arrays.asList(2938, 3088, 2009);
	public static final List<Integer> NO_LONGER_MANUFACTURED_PART_NUMBERS = Arrays.asList(2111, 2110, 2101);
	
	public boolean isValidPart(int partNumber);
	public boolean isPartOutOfStock(int partNumber);
	public boolean isPartNoLongerManufactured(int partNumber);
	public enum PartResponse
	{
		OUT_OF_STOCK,
		NO_LONGER_MANUFACTURED
	}
	// Submit part for manufacture and delivery.
	public PartResponse submitPartForManufactureAndDelivery(
	int partNumber,
	int quantity,
	DeliveryAddress deliveryAddress);
}
