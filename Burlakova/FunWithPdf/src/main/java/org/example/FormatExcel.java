package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FormatExcel {
    public static void main(String[] args) throws IOException {
        version2();
    }

    private static void version1(){
        try(XSSFWorkbook newWorkbook = new XSSFWorkbook(); OutputStream outputStream = Files.newOutputStream(Path.of("test.xlsx"));)
        {
            XSSFSheet sheet = newWorkbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("тест");

            newWorkbook.write(outputStream);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private static void version2() throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph tmpParagraph = document.createParagraph();
        XWPFRun tmpRun = tmpParagraph.createRun();
        tmpRun.setText("Test");
        tmpRun.setFontSize(18);
        document.write(Files.newOutputStream(Path.of("test2.docx")));
        document.close();
    }
}
