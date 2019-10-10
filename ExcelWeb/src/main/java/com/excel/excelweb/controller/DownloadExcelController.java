package com.excel.excelweb.controller;

import com.excel.excelweb.pojo.Student;
import com.excel.excelweb.util.ExcelHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("generateAStuExcel")
    public String GenerateAStuExcel(HttpServletResponse response) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Student s1 = new Student("1", "s1", 12, dateFormat.parse("1997-05-17"));
            Student s2 = new Student("2", "s2", 13, dateFormat.parse("1997-05-16"));
            Student s3 = new Student("3", "s3", 15, dateFormat.parse("1997-05-08"));
            Student s4 = new Student("4", "s4", 13, dateFormat.parse("1997-05-19"));
            List<Student> members = new ArrayList<Student>();
            members.add(s1);
            members.add(s2);
            members.add(s3);
            members.add(s4);

            Map<String, List<String>> map = new HashMap<>();
            List<String> memberInfos = null;
            for (Student s :
                    members
            ) {
                memberInfos = new ArrayList<>();
                memberInfos.add(s.getStuCode());
                memberInfos.add(s.getName());
                memberInfos.add(s.getAge().toString());
                memberInfos.add(s.getBirthDate().toString());
                map.put(s.getStuCode(),memberInfos);
            }
            //生成Excel
            String[] titles = new String[]{"学号","名字","年龄","生日"};
            HSSFWorkbook workbook = ExcelHelper.GenerateWorkbook(titles,map);
            //生成Excel
            String fileName = "WebExcel.xls";
            ExcelHelper.exportExcelByWeb(response,workbook,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "生成成功";
    }
}
