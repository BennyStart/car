package com.ben.common.excel;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelDownService {

	/**
	 * 数据生产EXCEL
	 * 
	 * @param headers表头
	 * @param contents内容
	 */
	public HSSFWorkbook createExcel(List<String> headers, List<Map<Integer, String>> contents) {
		// 1.创建一个workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2.在workbook中添加一个sheet，对应Excel中的一个sheet
		HSSFSheet sheet = wb.createSheet("TestDown");
		// 3.创建单元格，设置值表头，设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置表头
		HSSFRow row = sheet.createRow((int) 0);
		for (int i = 0; i < headers.size(); i++) {
			HSSFCell cell = row.createCell(i);
			String header = headers.get(i);
			cell.setCellStyle(style);
			cell.setCellValue(header);
		}

		// 设置表内容
		for (int i = 0; i < contents.size(); i++) {
			HSSFRow contentRow = sheet.createRow((int) i + 1);
			Map<Integer, String> map = contents.get(i);
			for (Entry<Integer, String> entry : map.entrySet()) {
				HSSFCell cell = contentRow.createCell(entry.getKey());
				cell.setCellStyle(style);
				cell.setCellValue(entry.getValue());
			}
		}

		return wb;
	}

}
