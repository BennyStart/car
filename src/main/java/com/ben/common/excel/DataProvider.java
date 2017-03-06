package com.ben.common.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataProvider {

	public DataProvider(List<Row> rows, Map<String, String> urlParams, Map<String, String> configParams, int sheetIndex,
			String sheetName) {
		this.rows = rows;
		this.urlParams = urlParams;
		this.configParams = configParams;
		this.sheetIndex = sheetIndex;
		this.sheetName = sheetName;
	}

	private List<Row> rows;

	private Map<String, String> urlParams;

	private Map<String, String> configParams;

	private int sheetIndex;

	private String sheetName;

	public String getUrlParam(String name) {
		if (null == urlParams) {
			return null;
		}
		return urlParams.get(name);
	}

	public Set<String> getUrlParamKeys() {
		if (null == urlParams) {
			return null;
		} else {
			return urlParams.keySet();
		}
	}

	public Set<String> getConfigParamKeys() {
		if (null == configParams) {
			return null;
		} else {
			return configParams.keySet();
		}
	}

	public String getConfigParam(String name) {
		if (null == configParams) {
			return null;
		} else {
			return configParams.get(name);
		}
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 
	 * Description : 根据参数名，获取参数的值
	 */
	public String getParam(String name) {
		String urlParam = this.getUrlParam(name);
		if (urlParam == null) {
			return this.getConfigParam(name);
		} else {
			return urlParam;
		}
	}

	public List<Row> getRows() {
		if (this.rows == null) {
			return null;
		} else {
			return new ArrayList<Row>(rows);
		}
	}

	public Row getRow(int index) {
		if (null == rows) {
			return null;
		} else {
			return rows.get(index);
		}
	}
}
