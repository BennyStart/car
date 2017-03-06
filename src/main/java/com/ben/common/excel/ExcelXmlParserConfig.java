package com.ben.common.excel;

import java.util.Map;

/**
 * Description: excel模板中工作薄(sheet)的解析配置对象
 */
public class ExcelXmlParserConfig {
	// 解析起始行
	private int startIndex = 1;
	// 各列与属性的映射关系配置
	private Map<String, Integer> columns;
	// 业务操作额外参数配置，暂不支持宏解析
	private Map<String, String> params;

	// bean名称
	private String beanName;

	/**
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the columns
	 */
	public Map<String, Integer> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Map<String, Integer> columns) {
		this.columns = columns;
	}

	/**
	 * @return the params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName
	 *            the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

}
