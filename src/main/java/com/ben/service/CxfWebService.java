package com.ben.service;

import javax.jws.WebService;

@WebService
public interface CxfWebService {

	public String testWebService(int num, String name);
}
