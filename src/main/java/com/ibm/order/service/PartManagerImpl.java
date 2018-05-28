package com.ibm.order.service;

import org.springframework.stereotype.Service;

import com.ibm.order.model.DeliveryAddress;

@Service
public class PartManagerImpl implements PartManager{

	@Override
	public boolean isValidPart(int partNumber) {
		return VALID_PART_NUMBERS.contains(partNumber);
	}

	@Override
	public PartResponse submitPartForManufactureAndDelivery(int partNumber, int quantity,
			DeliveryAddress deliveryAddress) {
		if(isPartOutOfStock(partNumber))
			return PartResponse.OUT_OF_STOCK;
		else if(isPartNoLongerManufactured(partNumber))
			return PartResponse.NO_LONGER_MANUFACTURED;
		return null;
	}

	@Override
	public boolean isPartOutOfStock(int partNumber) {
		return OUT_OF_STOCK_PART_NUMBERS.contains(partNumber);
	}

	@Override
	public boolean isPartNoLongerManufactured(int partNumber) {
		return NO_LONGER_MANUFACTURED_PART_NUMBERS.contains(partNumber);
	}

}
