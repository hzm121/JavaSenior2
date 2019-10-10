package com.excel.excelweb.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {
    public static Workbook getADemoExcelForTest(){
        HSSFWorkbook Workbook = new HSSFWorkbook();
        HSSFSheet sheet = Workbook.createSheet();
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellValue("下载Excel");
        return Workbook;
    }

}
