package com.ben.common;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.ben.service.CxfWebService;

public class Test {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
		svr.setServiceClass(CxfWebService.class);
		svr.setAddress("http://localhost:8333/car/webservice/cxfWebService");
		CxfWebService hw = (CxfWebService) svr.create();
		hw.testWebService(1, "2");
	}

}
