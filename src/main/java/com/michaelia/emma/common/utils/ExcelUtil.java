package com.michaelia.emma.common.utils;

import com.michaelia.emma.common.BusinessException;
import com.michaelia.emma.enums.GlobleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Excel导入/导出工具类
 */
@SuppressWarnings("all")
@Slf4j
public class ExcelUtil {

    /**
     * 判断是不是Excel文件
     *
     * @param contentType
     * @return
     */
    public static boolean isExcel(String contentType) {
        Assert.isTrue(StringUtils.isNotBlank(contentType), "参数不能为空");
        return contentType.contains("excel") || contentType.contains("spreadsheetml");
    }

    /**
     * 根据流获Workbook对象
     *
     * @param in
     * @return
     */
    public static Workbook getWorkbook(InputStream in) {
        Workbook workbook;
        try {
            workbook = new HSSFWorkbook(in);
        } catch (Exception e) {
            try {
                workbook = new XSSFWorkbook(in);
            } catch (Exception e1) {
                throw new BusinessException(GlobleEnum.SYS_ILLEGAL_FILE.getCode(), GlobleEnum.SYS_ILLEGAL_FILE.getMessage());
            }
        }
        return workbook;
    }

    /**
     * 导出
     *
     * @param response
     * @param fileName
     * @param wb
     */
    public static void setHeaderAndExport(HttpServletResponse response, String fileName, Workbook wb) {
        try {
            setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Workbook
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     * @author wuyong
     */
    public static Workbook getWorkbook(Workbook wb, String version, String sheetName, String[] title, String[][] values) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            if (StringUtils.isNotBlank(version)) {
                if ("2007".equalsIgnoreCase(version)) {
                    wb = new XSSFWorkbook();
                } else {
                    wb = new HSSFWorkbook();
                }
            }
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        Row row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        // 声明列对象
        Cell cell = null;

        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    public static void addResult(Workbook workbook, Sheet sheet, Map<Integer, String> errorMap, Row titleRow, int dataRowBegin, int columnCount, String fileName) {
        while (!isBlankColumn(sheet, columnCount)) {
            ++columnCount;
        }
        while (isBlankColumn(sheet, columnCount) && isBlankColumn(sheet, columnCount - 1)) {
            --columnCount;
        }
        //sheet.setColumnWidth(columnCount, 20);
        // 创建结果标题
        Cell result = titleRow.createCell(columnCount, CellType.STRING);
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        result.setCellValue("结果");
        result.setCellStyle(titleStyle);

        // 生成结果
        for (int i = dataRowBegin; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (isBlankRow(row)) {
                continue;
            }
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
            Cell cell = row.createCell(columnCount, CellType.STRING);
            cell.setCellStyle(dataStyle);
            String msg = errorMap.get(i);
            // 对错误信息标红
            if (StringUtils.isNotBlank(msg)) {
                Font font = workbook.createFont();
                font.setColor(Font.COLOR_RED);
                dataStyle.setFont(font);
            } else {
                msg = "成功";
            }
            cell.setCellValue(msg);
        }

        // 保存结果文件
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "static" + File.separator + "result" + File.separator;
            log.info("导入结果保存路径：{}", path);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
            workbook.write(bos);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isBlankRow(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (StringUtils.isNotBlank(getCellValue(row.getCell(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlankColumn(Sheet sheet, int columnNum) {
        int rowNum = sheet.getLastRowNum();
        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                if (StringUtils.isNotBlank(getCellValue(row.getCell(columnNum)))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int blankRowCount(Sheet sheet) {
        int count = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (isBlankRow(row)) {
                count++;
            }
        }
        return count;
    }
}
