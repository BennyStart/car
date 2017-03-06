package com.ben.common.excel;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ExcelLoadService implements ApplicationContextAware {

	private ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
		applicationContext = paramApplicationContext;
	}

	/**
	 * 执行导入方法
	 * 
	 * @param inputStream
	 * @param urlParams
	 * @param configs
	 * @return
	 * @throws Exception
	 */
	public String excute(InputStream inputStream, Map<String, String> urlParams, List<ExcelXmlParserConfig> configs)
			throws Exception {
		ExcelXmlParserConfig config = configs.get(0);
		// 获取processor
		if (config == null || config.getBeanName().equals("")) {
			return "解析配置文件中process的bean名称没有配置";
		}
		ExcelImportProcessor processor = getProcessor(config.getBeanName());
		if (null == processor) {
			return config.getBeanName() + "的bean不存在";
		}
		// 解析excel的业务数据
		ExcelParser parser = applicationContext.getBean(ExcelParser.class);

		List<Row> rows = parser.parser(inputStream, config);
		// 设置业务数据
		DataProvider provider = new DataProvider(rows, urlParams, config.getParams(), 0, ""); // 为配置文件的参数
		return processor.excute(provider);

	}

	/**
	 * 获取bean
	 * 
	 * @param beanName
	 * @return
	 */
	private ExcelImportProcessor getProcessor(String beanName) {
		return applicationContext.getBean(beanName, ExcelImportProcessor.class);

	}

}
