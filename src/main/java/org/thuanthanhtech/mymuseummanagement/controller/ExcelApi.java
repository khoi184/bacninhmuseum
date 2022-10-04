package org.thuanthanhtech.mymuseummanagement.controller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thuanthanhtech.mymuseummanagement.config.ExcelExporter;
import org.thuanthanhtech.mymuseummanagement.entity.News;
import org.thuanthanhtech.mymuseummanagement.service.NewsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/excel")
public class ExcelApi {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ExcelExporter excelExporter;

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<News>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        List<News> newsList = excelExporter.importNewsToExcel(files);
        return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
    }


    @GetMapping("/news/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=news_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<News> listNews = newsService.getAll();

        ExcelExporter excelExporter = new ExcelExporter(listNews);

        excelExporter.export(response);
    }
}
