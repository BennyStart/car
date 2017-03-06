package com.ben.service.impl;

import javax.jws.WebService;

import com.ben.service.CxfWebService;

@WebService(endpointInterface = "com.ben.service.CxfWebService", serviceName = "CxfWebService")
public class CxfWebServiceImpl implements CxfWebService {

	@Override
	public String testWebService(int num, String name) {
		System.out.println(num + name);
		return "true";
	}

}
