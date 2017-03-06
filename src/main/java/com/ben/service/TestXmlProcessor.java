package com.ben.service;

import org.springframework.stereotype.Service;

import com.ben.common.excel.DataProvider;
import com.ben.common.excel.ExcelImportProcessor;

@Service
public class TestXmlProcessor implements ExcelImportProcessor {

	@Override
	public String excute(DataProvider provider) {
		System.out.println(provider.getRows().get(0).getString("username"));
		System.out.println(provider.getRows().get(0).getString("sex"));
		return null;
	}

}
