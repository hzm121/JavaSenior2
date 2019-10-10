package com.excel.excelweb.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExcelHelper {
    //新建一个测试用的Excel
    public static Workbook getADemoExcelForTest() {
        HSSFWorkbook Workbook = new HSSFWorkbook();
        HSSFSheet sheet = Workbook.createSheet();
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellValue("下载Excel");
        return Workbook;
    }

    /**
     * 设置Exceltitle
     *
     * @param row    要设置标题的行
     * @param titles 各个标题
     * @param offset 从第几个单元格开始添加标题
     * @param style  单元格风格
     */
    private static void setExcelTitle(HSSFRow row, String[] titles, int offset, HSSFCellStyle style) {
        HSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(offset + i);
            cell.setCellStyle(style);
            cell.setCellValue(titles[i]);
        }
    }

    /**
     * 生成ExcelBody
     *
     * @param sheet        要添加的表
     * @param map          数据源
     * @param style        单元格分格
     * @param rowoffset    从第几行开始添加
     * @param cloumnoffset 从第几列开始添加
     */
    private static void setExcelBody(HSSFSheet sheet, Map<String, List<String>> map, HSSFCellStyle style, int rowoffset, int cloumnoffset) {
        HSSFRow row = null;
        HSSFCell cell = null;
        int i = 0;
        for (String key : map.keySet()) {
            row = sheet.createRow(rowoffset + i);//创建行
            List<String> memberInfos = map.get(key);
            for (int j = 0; j < memberInfos.size(); j++) {
                cell = row.createCell(cloumnoffset + j);//生成单元格
                cell.setCellStyle(style);
                cell.setCellValue(memberInfos.get(j));
            }
            i++;
        }
    }


    /**
     * 生成Excel
     *
     * @param titles 表头
     * @param map    数据源（注意里面的数据顺序和表头对应）
     * @return
     */
    public static HSSFWorkbook GenerateWorkbook(String[] titles, Map<String, List<String>> map) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        ExcelHelper.setExcelTitle(titleRow, titles, 0, style);
        ExcelHelper.setExcelBody(sheet, map, style, 1, 0);
        return workbook;
    }

    //导出Excel（web）
    public static void exportExcelByWeb(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        OutputStream os = null;
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel;charset=UTF-8");
            os = response.getOutputStream();
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
