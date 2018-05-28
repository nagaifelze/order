package com.ibm.order.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements Security {
	@Override
	public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) {
		if("XXX-1234-ABCD-1234".equalsIgnoreCase(dealerid))
			return true;
		return false;
	}
}
