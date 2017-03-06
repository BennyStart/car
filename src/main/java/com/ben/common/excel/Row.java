package com.ben.common.excel;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * ExcelParserRow Excel解析后的行数据对象
 */
public class Row {

	private final Logger logger = LoggerFactory.getLogger(Row.class);

	// Excel行数据信息 String字段名 Cell单元格数据对象，POI对象
	private Map<String, Cell> rowMap;

	// 传递使用Poi解析后的Excel行数据信息
	public Row(Map<String, Cell> rowMap) {
		super();
		this.rowMap = rowMap;
	}

	/**
	 * 将单元格的内容以Long形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 * @throws Exception
	 */
	public Boolean getBoolean(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Boolean.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以Long形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 * @throws Exception
	 */
	public Long getLong(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Long.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以String形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 */
	public String getString(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), String.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格中的字符串时间以Date类型返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @param dataFormat
	 *            指定日期格式
	 * @return Date 时间日期
	 */
	public Date getCellDate(String fieldName, String dataFormat) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Date.class, dataFormat);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以Double形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 */
	public Double getDouble(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Double.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以Float形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 */
	public Float getFloat(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Float.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以对象形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 */
	public Object getObject(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return getCellValue(cell);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将单元格的内容以Date形式返回
	 * 
	 * @param fieldName
	 *            excel中字段名
	 * @return
	 */
	public Date getDate(String fieldName) {
		try {
			Cell cell = rowMap.get(fieldName);
			return typeCast(getCellValue(cell), Date.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Description : 获取单元格内容
	 * 
	 * @param cell
	 *            单元格
	 * @return Object
	 */
	private Object getCellValue(Cell cell) {
		Object value = null;
		if (cell == null) {
			return value;
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			// 字符串型
			value = cell.getRichStringCellValue().getString().trim();
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			// 数值型
			if (DateUtil.isCellDateFormatted(cell)) {
				// 日期
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			// 公式型
			try {
				value = cell.getRichStringCellValue().getString().trim();
			} catch (IllegalStateException e) {
				value = cell.getNumericCellValue();
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			// 空值
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			// 布尔型
			value = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			// 错误
			throw new RuntimeException("Excel格式错误：行：" + cell.getRowIndex() + ",列：" + cell.getColumnIndex());
		default:
			throw new RuntimeException("未知数据类型：行：" + cell.getRowIndex() + ",列：" + cell.getColumnIndex());
		}
		return value;
	}

	/**
	 * Description : 数据类型转换
	 * 
	 * @param <T>
	 * @param obj
	 *            被转换数据
	 * @param classType
	 *            转换类型
	 * @param pattern
	 *            日期格式
	 * @return 转换后的数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private <T> T typeCast(Object obj, Class<T> classType, String pattern) throws Exception {
		// 如果为空时返回
		if (null == obj) {
			return null;
		}
		T result = null;
		if (obj.getClass().equals(classType)) {
			// 类型相同时
			result = (T) obj;
		} else if (String.class.equals(classType)) {
			// String类型
			if (obj instanceof Double) {
				Double _obj = (Double) obj;
				long _longObj = _obj.longValue();
				if (_obj.doubleValue() == _longObj) {
					result = (T) (String.valueOf(_longObj));
				} else {
					java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
					nf.setGroupingUsed(false);
					nf.setMaximumFractionDigits(10);
					result = (T) (nf.format(_obj));
				}
			} else {
				result = (T) obj.toString();
			}
		} else if (Long.class.equals(classType)) {
			// Long类型
			if (obj instanceof Double) {
				result = (T) new Long(((Double) obj).longValue());
			} else {
				result = (T) new Long(obj.toString().trim());
			}
		} else if (Double.class.equals(classType)) {
			// Double类型
			result = (T) new Double(obj + "");
		} else if (Date.class.equals(classType)) {
			// Date类型
			if (obj instanceof String && pattern != null) {
				// 2015-6-15.2 如果excel中单元格是时间类型字符串,格式化成时间类型
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				try {
					result = (T) sdf.parseObject((String) obj);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			} else {
				result = (T) HSSFDateUtil.getJavaDate((Double) obj);
			}
		} else if (Boolean.class.equals(classType)) {
			// Boolean类型
			result = (T) new Boolean(obj + "");
		} else if (Float.class.equals(classType)) {
			// Float类型
			result = (T) new Float(obj + "");
		} else if (Integer.class.equals(classType)) {
			// Integer类型
			if (obj instanceof Double) {
				result = (T) new Integer(((Double) obj).intValue());
			} else {
				result = (T) new Integer(obj.toString().trim());
			}
		} else {
			throw new Exception("无法识别的数据类型:" + classType);
		}
		return result;
	}

	/**
	 * 2015-6-15.3 转换为对象
	 * 
	 * @param <T>
	 * @param <T>
	 * @param clazz
	 *            被转化的对象类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T toObject(Class<T> clazz) {
		if (rowMap == null || clazz == null) {
			return null;
		}
		BeanWrapper target = new BeanWrapperImpl(clazz);
		// 获取转换后对象属性描述
		PropertyDescriptor[] pds = target.getPropertyDescriptors();

		for (int i = 0; i < pds.length; i++) {
			Object value = null;
			// 获取属性的类型
			Class<?> propertyType = pds[i].getPropertyType();
			if (propertyType.equals(Short.class) || "short".equals(propertyType.getName())) {
				// Short
				value = this.getLong(pds[i].getName());
			} else if (propertyType.equals(Integer.class) || "int".equals(propertyType.getName())) {
				// Integer
				value = this.getLong(pds[i].getName());
			} else if (propertyType.equals(Long.class) || "long".equals(propertyType.getName())) {
				// Long
				value = this.getLong(pds[i].getName());
			} else if (propertyType.equals(Float.class) || "float".equals(propertyType.getName())) {
				// Float
				value = this.getFloat(pds[i].getName());
			} else if (propertyType.equals(Double.class) || "double".equals(propertyType.getName())) {
				// Double
				value = this.getDouble(pds[i].getName());
			} else if (propertyType.equals(Boolean.class) || "boolean".equals(propertyType.getName())) {
				// Boolean
				value = this.getBoolean(pds[i].getName());
			} else if (propertyType.equals(Date.class)) {
				// Date
				value = this.getDate(pds[i].getName());
			} else if (propertyType.equals(String.class)) {
				// String
				value = this.getString(pds[i].getName());
			}

			// 设置属性的值
			if (value != null) {
				if (target.isWritableProperty(pds[i].getName())) {
					target.setPropertyValue(pds[i].getName(), value);
				}
			}
		}
		return (T) target.getWrappedInstance();
	}
}
