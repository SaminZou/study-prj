package com.samin.generator.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TemplateEngine templateEngine;

    public void report() throws IOException, DocumentException {
        // 创建PDF文档
        Document document = new Document();

        // 创建PDF写入器
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("C:\\Users\\samin\\Desktop\\report.pdf")));

        // 打开文档
        document.open();

        // 创建Thymeleaf上下文
        Context context = new Context();

        // 设置要传递给Thymeleaf模板的数据（例如，从后端获取的报表数据）
        context.setVariable("name", "标题");
        context.setVariable("content", "内容");

        // 渲染 Thymeleaf 模板
        String htmlContent = templateEngine.process("report2PDF", context);

        // 使用iText将HTML内容转换为PDF
        XMLWorkerHelper.getInstance()
                .parseXHtml(writer, document, new ByteArrayInputStream(htmlContent.getBytes()));

        // 关闭文档
        document.close();
    }
}
