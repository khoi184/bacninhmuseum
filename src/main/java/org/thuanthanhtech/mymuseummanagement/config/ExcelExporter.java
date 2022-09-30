package org.thuanthanhtech.mymuseummanagement.config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thuanthanhtech.mymuseummanagement.entity.News;

@Service
public class ExcelExporter {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<News> listNews;

    public ExcelExporter(List<News> listNews) {
        this.listNews = listNews;
        workbook = new XSSFWorkbook();
    }

    public List<News> importNewsToExcel(MultipartFile files) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        List<News> newsList = new ArrayList<>();
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                News news = new News();

                XSSFRow row = worksheet.getRow(index);
                Long id = (long) row.getCell(0).getNumericCellValue();

                news.setId(id);
                news.setName(row.getCell(1).getStringCellValue());
                news.setContent(row.getCell(2).getStringCellValue());
                news.setSlug(row.getCell(3).getStringCellValue());
                news.setAuthor(row.getCell(3).getStringCellValue());
                news.setTitle(row.getCell(3).getStringCellValue());

                newsList.add(news);
            }
        }
        return newsList;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("News");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "News ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Slug", style);
        createCell(row, 3, "Content", style);
        createCell(row, 4, "Author", style);
        createCell(row, 5, "Title", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (News user : listNews) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getId().toString(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getContent(), style);
            createCell(row, columnCount++, user.getSlug(), style);
            createCell(row, columnCount++, user.getAuthor(), style);
            createCell(row, columnCount++, user.getTitle(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}