package com.javasenior.excel.demo;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class demo {

    public static void main(String[] args)  {
        //创建一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");//新建sheet页
        HSSFRow firstRow = sheet.createRow(0);//新建一行
        //合并单元格
        CellRangeAddress range1 = new CellRangeAddress(0,3,0,4);
        sheet.addMergedRegion(range1);

        //设置单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//单元格居中
        //设置背景色
        short blue1Index = IndexedColors.BLUE1.index;//获取颜色
        cellStyle.setFillForegroundColor(blue1Index);//设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置填充模式

        firstRow.setRowStyle(cellStyle);
        HSSFCell cell = firstRow.createCell(5);//在第6行建立单元格
        cell.setCellValue("TestExceL12345");
        //保存excel到磁盘
        FileOutputStream fos = null;
        String fileName = "testExcel.xls";
        try {
            fos = new FileOutputStream(new File("C:/Users/26963/Desktop/"+fileName));
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
