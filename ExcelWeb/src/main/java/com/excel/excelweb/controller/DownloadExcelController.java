package com.excel.excelweb.controller;

import com.excel.excelweb.util.ExcelHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
public class DownloadExcelController {
    @RequestMapping("downloadexcel")
    public void DownExcel(HttpServletResponse response) {
        Workbook workbook = ExcelHelper.getADemoExcelForTest();
        String fileName = "ExcelWeb.xls";
        OutputStream outputStream = null;
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/msexcel;charset=UTF-8");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("导出成功");
        }
    }
}
