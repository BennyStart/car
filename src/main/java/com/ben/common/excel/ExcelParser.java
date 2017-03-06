package com.ben.common.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Description:对Excel的数据解析
 */
public class ExcelParser {

	public List<Row> parser(InputStream inputStream, ExcelXmlParserConfig excelCongfig) {

		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(inputStream);
		} catch (Exception e) {
			return null;
		}

		try {
			// excel 输入流文件为空
			if (inputStream == null) {
				return null;
			}

			// 把sheet指定在第一页
			Sheet sheet = workBook.getSheetAt(0);
			if (sheet == null) {
				return null;
			}

			if (excelCongfig == null) {
				return null;
			}
			// 解析开始行
			int startIndex = excelCongfig.getStartIndex();
			if (startIndex > sheet.getLastRowNum() + 1) {
				return null;
			}

			// 获取列对应的属性
			Map<String, Integer> columns = excelCongfig.getColumns();
			if (columns == null) {
				return null;
			}

			// 获取sheet中合并单元格的信息
			List<MergeCellsInfo> mergeCellsInfoLists = getMergeCellsInfo(sheet);

			// Excel解析后的行数据对象集合
			List<Row> rows = new ArrayList<Row>();

			// 遍历行
			for (int currentRow = startIndex - 1; currentRow <= sheet.getLastRowNum(); currentRow++) {
				org.apache.poi.ss.usermodel.Row sheetRow = sheet.getRow(currentRow); // 取出第一行
				// 一行数据
				Map<String, Cell> rowMap = new HashMap<String, Cell>();
				for (String attr : columns.keySet()) {
					Cell c = null;
					try {
						if (columns.get(attr) - 1 >= 0) {
							c = sheetRow.getCell(columns.get(attr) - 1);
						}
					} catch (NullPointerException e) {

					}
					Cell cell = isMergedRegion(sheet, c, mergeCellsInfoLists);
					rowMap.put(attr, cell);
				}
				// 校验是否是空行(cell 为空字符串或者没有值)
				if (!isEmptyRow(rowMap)) {
					Row excelParserRow = new Row(rowMap);
					rows.add(excelParserRow);
				}
			}
			return rows;
		} catch (Exception e) {
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	public List<Row> parserSheets(Sheet sheet, ExcelXmlParserConfig excelCongfig, int sheetIndex) {
		try {
			if (sheet == null) {
				return null;
			}

			if (excelCongfig == null) {
				return null;
			}
			// 解析开始行
			int startIndex = excelCongfig.getStartIndex();
			if (startIndex > sheet.getLastRowNum() + 1) {
				return null;
			}

			// 获取列对应的属性
			Map<String, Integer> columns = excelCongfig.getColumns();
			if (columns == null) {
				return null;
			}

			// 获取sheet中合并单元格的信息
			List<MergeCellsInfo> mergeCellsInfoLists = getMergeCellsInfo(sheet);

			// Excel解析后的行数据对象集合
			List<Row> rows = new ArrayList<Row>();

			// 遍历行
			for (int currentRow = startIndex - 1; currentRow <= sheet.getLastRowNum(); currentRow++) {
				org.apache.poi.ss.usermodel.Row sheetRow = sheet.getRow(currentRow); // 取出第一行
				// 一行数据
				Map<String, Cell> rowMap = new HashMap<String, Cell>();
				for (String attr : columns.keySet()) {
					Cell c = null;
					try {
						if (columns.get(attr) - 1 >= 0) {
							c = sheetRow.getCell(columns.get(attr) - 1);
						}
					} catch (NullPointerException e) {

					}
					Cell cell = isMergedRegion(sheet, c, mergeCellsInfoLists);
					rowMap.put(attr, cell);
				}
				// 校验是否是空行(cell 为空字符串或者没有值)
				if (!isEmptyRow(rowMap)) {
					Row excelParserRow = new Row(rowMap);
					rows.add(excelParserRow);
				}
			}
			return rows;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 判断一行数据是否是空行
	 * 
	 * @param rowMap
	 * @return
	 */
	private boolean isEmptyRow(Map<String, Cell> rowMap) {
		boolean isEmpty = true;
		if (rowMap == null) {
			return isEmpty;
		}
		for (String key : rowMap.keySet()) {
			Cell cell = rowMap.get(key);
			if (cell != null) { // 不为空并且不为空字符串
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					String str = cell.getStringCellValue().trim();
					if (str != null && !str.equals("")) {
						isEmpty = false;
					}
				} else {
					isEmpty = false;
				}
			}

		}
		return isEmpty;
	}

	/**
	 * 获取sheet中合并单元格的信息
	 * 
	 * @param sheet
	 * @return
	 */
	private List<MergeCellsInfo> getMergeCellsInfo(Sheet sheet) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		List<MergeCellsInfo> mergeCellsInfoLists = new ArrayList<MergeCellsInfo>();
		MergeCellsInfo mergeCellsInfo = null;
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			mergeCellsInfo = new MergeCellsInfo();
			CellRangeAddress ca = sheet.getMergedRegion(i);
			mergeCellsInfo.setFirstColumn(ca.getFirstColumn());
			mergeCellsInfo.setFirstRow(ca.getFirstRow());
			mergeCellsInfo.setLastColumn(ca.getLastColumn());
			mergeCellsInfo.setLastRow(ca.getLastRow());
			mergeCellsInfoLists.add(mergeCellsInfo);
		}
		return mergeCellsInfoLists;
	}

	/**
	 * 判断该单元格是否在合并单元格范围之内, 如果是, 则返回该合并单元格的值
	 * 
	 * @param sheet
	 * @param cell
	 * @return
	 */
	private Cell isMergedRegion(Sheet sheet, Cell cell, List<MergeCellsInfo> mergeCellsInfoLists) {
		if (cell != null) {
			for (MergeCellsInfo mergeCellsInfo : mergeCellsInfoLists) {
				int mergeCellFirstRow = mergeCellsInfo.getFirstRow();
				int mergeCellLastRow = mergeCellsInfo.getLastRow();
				int mergeCellFirstColumn = mergeCellsInfo.getFirstColumn();
				int mergeCellLastColumn = mergeCellsInfo.getLastColumn();
				if (cell.getRowIndex() <= mergeCellLastRow && cell.getRowIndex() >= mergeCellFirstRow) {
					if (cell.getColumnIndex() <= mergeCellLastColumn && cell.getColumnIndex() >= mergeCellFirstColumn) {
						org.apache.poi.ss.usermodel.Row fRow = sheet.getRow(mergeCellFirstRow);
						Cell fCell = fRow.getCell(mergeCellFirstColumn);
						return fCell;
					}
				}
			}
		}
		return cell;
	}

	/**
	 * 合并单元格信息类
	 * 
	 * @author Phil
	 */
	private class MergeCellsInfo {
		// 起始列
		private int firstColumn;
		// 终止列
		private int lastColumn;
		// 起始行
		private int firstRow;
		// 终止行
		private int lastRow;

		// @return firstColumn
		public int getFirstColumn() {
			return firstColumn;
		}

		// @param firstColumn
		public void setFirstColumn(int firstColumn) {
			this.firstColumn = firstColumn;
		}

		// @return lastColumn
		public int getLastColumn() {
			return lastColumn;
		}

		// @param lastColumn
		public void setLastColumn(int lastColumn) {
			this.lastColumn = lastColumn;
		}

		// @return firstRow
		public int getFirstRow() {
			return firstRow;
		}

		// @param firstRow
		public void setFirstRow(int firstRow) {
			this.firstRow = firstRow;
		}

		// @return lastRow
		public int getLastRow() {
			return lastRow;
		}

		// @param lastRow
		public void setLastRow(int lastRow) {
			this.lastRow = lastRow;
		}
	}
}
