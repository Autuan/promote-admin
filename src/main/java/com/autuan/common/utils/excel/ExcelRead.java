package com.autuan.common.utils.excel;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final String SEPARATOR = "|";

    public ExcelRead() {
    }

    public static List<String> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws IOException {
        Workbook workbook = null;
        System.out.println("extensionName=========={}" + extensionName);
        if (extensionName.toLowerCase().equals("xls")) {
            System.out.println("XLS格式");
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals("xlsx")) {
            System.out.println("XLSX格式");
            workbook = new XSSFWorkbook(is);
        }

        return exportListFromExcel((Workbook)workbook, sheetNum);
    }

    public static List<String> exportListFromExcel(File file, int sheetNum) throws IOException {
        return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);
    }

    private static List<String> exportListFromExcel(Workbook workbook, int sheetNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<String> list = new ArrayList();
        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();

        for(int rowIx = minRowIx; rowIx <= maxRowIx; ++rowIx) {
            Row row = sheet.getRow(rowIx);
            StringBuilder sb = new StringBuilder();
            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();

            for(short colIx = minColIx; colIx <= maxColIx; ++colIx) {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue != null) {
                    switch(cellValue.getCellType()) {
                        case 0:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.println("==>" + cell.getDateCellValue());
                                sb.append("|" + cell.getDateCellValue());
                            } else {
                                sb.append("|" + (long)cellValue.getNumberValue());
                            }
                            break;
                        case 1:
                            sb.append("|" + cellValue.getStringValue());
                        case 2:
                        case 3:
                        case 5:
                        default:
                            break;
                        case 4:
                            sb.append("|" + cellValue.getBooleanValue());
                    }
                }else{
                    sb.append("|");
                }
            }

            list.add(sb.toString());
        }

        return list;
    }
}
