package com.ben.common.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ExcelXmlParserConfigManager {

	private final String EXCEL_CONFIG_FILE_SUFFIX = ".xml";

	/**
	 * XML解析
	 * 
	 * @param configName
	 * @return
	 */
	public List<ExcelXmlParserConfig> getExcelConfig(String configName) throws DocumentException {
		List<ExcelXmlParserConfig> configs = new ArrayList<ExcelXmlParserConfig>();
		InputStream inputStream = null;
		try {
			inputStream = getConfigInputStream(configName);
			if (inputStream == null) {
				return configs;
			}
			SAXReader reader = new SAXReader();
			Document doc = reader.read(inputStream);
			Element root = doc.getRootElement();
			if (null == root) { // 根节点是否为空
				return configs;
			}
			List<Element> elements = root.elements(XmlConstant.NODE_SHEET);
			if (null == elements) { // sheet节点是否为空
				return configs;
			}
			for (Element element : elements) {
				ExcelXmlParserConfig config = new ExcelXmlParserConfig();
				// 获取数据起始行
				String indexStr = element.elementText(XmlConstant.NODE_START_ROW_INDEX);
				if (indexStr != null) {
					config.setStartIndex(Integer.parseInt(indexStr));
				}
				// 获取processor节点的内容
				Element processorNode = element.element(XmlConstant.NODE_PROCESSOR);
				String beanName = processorNode.attributeValue(XmlConstant.ATTR_BEAN);
				if (beanName != null) {
					config.setBeanName(beanName);
				}

				// 获取columns节点
				Element columnsNode = element.element(XmlConstant.NODE_COLUMNS);
				List<Element> columnNodes = columnsNode.elements(XmlConstant.NODE_COLUMN);
				Map<String, Integer> columns = new HashMap<String, Integer>();
				for (Element col : columnNodes) {
					columns.put(col.attributeValue("name"), Integer.parseInt(col.attributeValue("index")));
				}
				config.setColumns(columns);

				// 获取params节点
				Element paramsNode = element.element(XmlConstant.NODE_PARAMS);
				if (paramsNode != null) {
					List<Element> paramNodes = paramsNode.elements(XmlConstant.NODE_PARAM);
					if (paramNodes != null) {
						Map<String, String> params = new HashMap<String, String>();
						for (Element param : paramNodes) {
							params.put(param.attributeValue(XmlConstant.ATTR_KEY),
									param.attributeValue(XmlConstant.ATTR_VALUE));
						}
						config.setParams(params);
					}
				}
				configs.add(config);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return configs;
	}

	/**
	 * 获取配置文件
	 * 
	 * @throws IOException
	 */
	private InputStream getConfigInputStream(String key) throws IOException {
		String name = new String(key + EXCEL_CONFIG_FILE_SUFFIX);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream in = classloader.getResourceAsStream("excel/" + name);
		return in;
	}

}
