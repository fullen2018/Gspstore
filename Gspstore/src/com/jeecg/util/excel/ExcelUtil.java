package com.jeecg.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.core.annotation.Excel;

public final class ExcelUtil {

	private ExcelUtil() {
	}

	/** Excel 文件要存放的位置 */
	public static String outputFile = "/zlxx/excel_demo.xls";

	/**
	 * 标题列表
	 */
	private static String[] HEAD_LIST = { "序号", "名字", "年龄", "备注" };

	private static String[] VALUE_LIST = { "01", "张代浩", "20", "1986-04-03",
			"........." };

	/**
	 * 字段列表
	 */
	private static String[] FIELD_LIST = { "index", "name", "age", "content" };

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------
		// List<String[]> list = new ArrayList<String[]>();
		// list.add(VALUE_LIST);
		// list.add(VALUE_LIST);
		// list.add(VALUE_LIST);
		// createExcel(outputFile, HEAD_LIST, list);

		// ------------------------------------------------------------
		// List<Map<String, Object>> dataList = new ArrayList<Map<String,
		// Object>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("index", "001");
		// map.put("name", "张三");
		// map.put("age", "22");
		// map.put("content", "大家好");
		// dataList.add(map);
		// dataList.add(map);
		// dataList.add(map);
		//
		// createExcel(outputFile, HEAD_LIST, FIELD_LIST, dataList);

		// -------------------------------------------------------------
		// readExcel(null);
		// --------------------------------------------------------------
		new ExcelUtil().testReadExcel();
	}

	/**
	 * 使用举例
	 * 
	 */
	public void testCreateExcel() {

		List<Map<String, Object>> dataList = getDataList();
		List<String> headList = getHeadList();
		List<String> fieldList = getFieldList();

		try {
			createExcel("TEST01.xls", headList, fieldList, dataList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 使用举例
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("static-access")
	public void testReadExcel() throws Exception {
		String excelUrl = "C:/javadeveloper/workspace/Mybatis_one/src/测试台账2003.xls";
		List<String[]> list = this.readExcel(excelUrl);
		for (String[] str : list) {
			for (String s : str) {
				//System.out.print(s + " | ");
			}
			//System.out.println("");
		}
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getDataList() {
		/**
		 * 数据集合
		 */
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		// 单行数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", "001");
		map.put("name", "张三");
		map.put("age", "22");
		map.put("content", "大家好");
		map.put("date", new Date());
		dataList.add(map);
		dataList.add(map);
		dataList.add(map);

		return dataList;
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<String> getHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("名字");
		headList.add("年龄");
		headList.add("出生");
		headList.add("备注");

		return headList;
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<String> getFieldList() {
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("index");
		fieldList.add("name");
		fieldList.add("age");
		fieldList.add("date");
		fieldList.add("content");

		return fieldList;
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void createExcel(String excel_name, String[] headList,
			String[] fieldList, List<Map<String, Object>> dataList)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.length; i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList[i])));
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void createExcel(String excel_name, List<String> headList,
			List<String> fieldList, List<Map<String, Object>> dataList)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.size(); i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList.get(i));
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.size(); i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList.get(i))));
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws HSSFWorkbook
	 */
	public static HSSFWorkbook createExcel(List<String> headList,
			List<String> fieldList, List<Map<String, Object>> dataList)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.size(); i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList.get(i));
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.size(); i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList.get(i))));
			}
			// ===============================================================
		}
		return workbook;
	}

	private static String objToString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof Date) {
				return null;// DateUtil.dateToString((Date)
							// obj,DateUtil.DATESTYLE_SHORT_EX);
			} else {
				return obj.toString();
			}
		}
	}

	/**
	 * 
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题部分
	 * @param valueList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void bulidExcel(String excel_name, String[] headList,
			List<String[]> valueList) throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================

		for (int n = 0; n < valueList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			String[] valueArray = valueList.get(n);
			// ===============================================================
			for (int i = 0; i < valueArray.length; i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(valueArray[i]);
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readExcel(String excel_name) throws Exception {
		// 结果集
		List<String[]> list = new ArrayList<String[]>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(
				excel_name));

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			HSSFRow hssfrow = hssfsheet.getRow(j);
			if(hssfrow!=null){
			int col = hssfrow.getPhysicalNumberOfCells();
			// 单行数据
			String[] arrayString = new String[col];
			for (int i = 0; i < col; i++) {
				HSSFCell cell = hssfrow.getCell(i);
				if (cell == null) {
					arrayString[i] = "";
				} else if (cell.getCellType() == 0) {
					// arrayString[i] = new Double(cell.getNumericCellValue()).toString();
					if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) { 
						  if (HSSFDateUtil.isCellDateFormatted(cell)) {    
						    Date d = cell.getDateCellValue();    
						    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");    
						    // DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						    arrayString[i] = formater.format(d);   
						   } else {    
						       arrayString[i] = new BigDecimal(cell.getNumericCellValue()).longValue()+"";    
						}
					}
				} else {// 如果EXCEL表格中的数据类型为字符串型
					arrayString[i] = cell.getStringCellValue().trim();
				}
			}
			list.add(arrayString);
		}
		}
		return list;
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> readExcelByList(String excel_name)
			throws Exception {
		// 结果集
		List<List<Object>> list = new ArrayList<List<Object>>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(
				excel_name));

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			HSSFRow hssfrow = hssfsheet.getRow(j);
			if (hssfrow != null) {
				int col = hssfrow.getPhysicalNumberOfCells();
				// 单行数据
				List<Object> arrayString = new ArrayList<Object>();
				for (int i = 0; i < col; i++) {
					HSSFCell cell = hssfrow.getCell(i);
					if (cell == null) {
						arrayString.add("");
					} else if (cell.getCellType() == 0) {
						arrayString.add(new Double(cell.getNumericCellValue())
								.toString());
					} else {// 如果EXCEL表格中的数据类型为字符串型
						arrayString.add(cell.getStringCellValue().trim());
					}
				}
				list.add(arrayString);
			}
		}
		return list;
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> readExcelByInputStream(
			InputStream inputstream) throws Exception {
		// 结果集
		List<List<Object>> list = new ArrayList<List<Object>>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数

		// //System.out.println("excel行数： "+hssfsheet.getPhysicalNumberOfRows());
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			HSSFRow hssfrow = hssfsheet.getRow(j);
			if (hssfrow != null) {
				int col = hssfrow.getPhysicalNumberOfCells();
				// 单行数据
				List<Object> arrayString = new ArrayList<Object>();
				for (int i = 0; i < col; i++) {
					HSSFCell cell = hssfrow.getCell(i);
					if (cell == null) {
						arrayString.add("");
					} else if (cell.getCellType() == 0) {
						arrayString.add(new Double(cell.getNumericCellValue())
								.toString());
					} else {// 如果EXCEL表格中的数据类型为字符串型
						arrayString.add(cell.getStringCellValue().trim());
					}
				}
				list.add(arrayString);
			}
		}
		return list;
	}
		/**
		 * 导入 excel
		 * @param file
		 * @param pojoClass
		 * @param pattern
		 * @return
		 */
		public static Collection importExcel(File file ,Class pojoClass) {
			Collection dist = new ArrayList<Object>();
			try {
			// 得到目标目标类的所有的字段列表
			Field filed[] = pojoClass.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			Map<String,Method> fieldSetMap = new HashMap<String,Method>();
			Map<String,Method> fieldSetConvertMap = new HashMap<String,Method>();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
			Field f = filed[i];
			// 得到单个字段上的Annotation
			Excel excel = f.getAnnotation(Excel.class);
			// 如果标识了Annotationd的话
			if (excel != null) {
			// 构造设置了Annotation的字段的Setter方法
			String fieldname = f.getName();
			String setMethodName = "set"
			+ fieldname.substring(0, 1).toUpperCase()
			+ fieldname.substring(1);
			// 构造调用的method，
			Method setMethod = pojoClass.getMethod(setMethodName,
			new Class[] { f.getType() });
			// 将这个method以Annotaion的名字为key来存入。
			//对于重名将导致 覆盖 失败，对于此处的限制需要
			fieldSetMap.put(excel.exportName(), setMethod);
			if(excel.importConvertSign()==1)
			{
			StringBuffer setConvertMethodName = new StringBuffer("set");
			setConvertMethodName.append(fieldname.substring(0, 1)
			.toUpperCase());
			setConvertMethodName.append(fieldname.substring(1));
			setConvertMethodName.append("Convert");
			Method getConvertMethod = pojoClass.getMethod(setConvertMethodName.toString(),
			new Class[] {String.class});
			fieldSetConvertMap.put(excel.exportName(), getConvertMethod);
			}
			}
			}
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(in);
			// // 得到第一页
			HSSFSheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();
			// 得到第一行，也就是标题行
			Row title = row.next();
			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
			Cell cell = cellTitle.next();
			String value = cell.getStringCellValue();
			titlemap.put(i, value);
			i = i + 1;
			}
			//用来格式化日期的DateFormat
			SimpleDateFormat sf;
			while (row.hasNext()) {
			// 标题下的第一行
			Row rown = row.next();
			// 行的所有列
			Iterator<Cell> cellbody = rown.cellIterator();
			// 得到传入类的实例
			Object tObject = pojoClass.newInstance();
			int k = 0;
			// 遍历一行的列
			while (cellbody.hasNext()) {
			Cell cell = cellbody.next();
			// 这里得到此列的对应的标题
			String titleString = (String) titlemap.get(k);
			// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
			if (fieldSetMap.containsKey(titleString)) {
			Method setMethod = (Method) fieldSetMap.get(titleString);
			//得到setter方法的参数
			Type[] ts = setMethod.getGenericParameterTypes();
			//只要一个参数
			String xclass = ts[0].toString();
			//判断参数类型
			if (fieldSetConvertMap.containsKey(titleString)) {
			fieldSetConvertMap.get(titleString).invoke(tObject,
			cell.getStringCellValue());
			} else {
			if (xclass.equals("class java.lang.String")) {
				//先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
				cell.setCellType(Cell.CELL_TYPE_STRING);
			setMethod.invoke(tObject, cell
			.getStringCellValue());
			}
			else if (xclass.equals("class java.util.Date")) {
				try{
					setMethod.invoke(tObject, cell
							.getDateCellValue());
												
				}catch(Exception e){
					
				}
			}
			else if (xclass.equals("class java.lang.Boolean")) {
			setMethod.invoke(tObject, cell
			.getBooleanCellValue());
			}
			else if (xclass.equals("class java.lang.Integer")) {
			setMethod.invoke(tObject, new Integer(cell
			.getStringCellValue()));
			}else if(xclass. equals("class java.lang.Long"))
			{
			setMethod.invoke(tObject,new Long( cell.getStringCellValue()));
			}
			}
			}
			// 下一列
			k = k + 1;
			}
			dist.add(tObject);
			}
			} catch (Exception e) {
			e.printStackTrace();
			return null;
			}
			return dist;
		}
}
