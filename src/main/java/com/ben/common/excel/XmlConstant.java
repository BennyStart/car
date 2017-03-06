package com.ben.common.excel;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Excel模板导入模块常量定义类
 */
public class XmlConstant {
	public static final String NODE_CONFIG = "config";
	/**
	 * Excel中工作簿节点名
	 */
	public static final String NODE_SHEET = "sheet";// 需要加上注解，写明表达的含义，参照工作簿的例子

	public static final String NODE_COLUMNS = "columns";

	public static final String NODE_COLUMN = "column";

	public static final String NODE_START_ROW_INDEX = "startRowIndex";

	public static final String NODE_INDEX = "index";

	public static final String NODE_PARAMS = "params";

	public static final String NODE_PARAM = "param";

	public static final String NODE_PROCESSOR = "processor";

	public static final String ATTR_KEY = "key";

	public static final String ATTR_VALUE = "value";

	public static final String ATTR_BEAN = "bean";

}
