package com.ben.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ben.common.excel.ExcelDownService;
import com.ben.common.excel.ExcelLoadService;
import com.ben.common.excel.ExcelXmlParserConfig;
import com.ben.common.excel.ExcelXmlParserConfigManager;

@Controller
@RequestMapping("/excel")
public class ExcelController implements ApplicationContextAware {

	private static final String EXCEL = "_excel";

	private ApplicationContext applicationContext = null;

	/**
	 * 导入
	 * 
	 * @return
	 */
	@RequestMapping("/upload")
	public @ResponseBody String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		InputStream inputStream = null;
		try {
			// 获取配置文件名
			String configName = request.getParameter(EXCEL);
			// 获取url参数
			Map<String, String> urlParams = new HashMap<String, String>();
			Map<String, String[]> urlMap = request.getParameterMap();
			for (String str : urlMap.keySet()) {
				urlParams.put(str, request.getParameter(str));
			}

			// 获取配置信息
			ExcelXmlParserConfigManager parserConfigManager = applicationContext
					.getBean(ExcelXmlParserConfigManager.class);
			List<ExcelXmlParserConfig> configs = new ArrayList<ExcelXmlParserConfig>();
			inputStream = file.getInputStream();
			if (null == configName) {
				return null;
			}
			configs = parserConfigManager.getExcelConfig(configName);
			ExcelLoadService excelLoadService = applicationContext.getBean(ExcelLoadService.class);
			return excelLoadService.excute(inputStream, urlParams, configs);
		} catch (Exception e) {
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@RequestMapping("/download")
	public @ResponseBody String download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name) {
		ExcelDownService excelDownService = applicationContext.getBean(ExcelDownService.class);
		HSSFWorkbook wb = null;
		List<String> headers = new ArrayList<String>();
		headers.add("车名");
		headers.add("价格");
		headers.add("时间");
		List<Map<Integer, String>> contents = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		map1.put(0, "宝马");
		map1.put(1, "100000");
		map1.put(2, new Date().toString());
		Map<Integer, String> map2 = new HashMap<Integer, String>();
		map2.put(0, "奥迪");
		map2.put(1, "100001");
		map2.put(2, new Date().toString());
		contents.add(map1);
		contents.add(map2);
		wb = excelDownService.createExcel(headers, contents);

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=TestDown.xls");
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
		applicationContext = paramApplicationContext;
	}
}
