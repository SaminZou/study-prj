package com.samin.generator.service;

import com.lowagie.text.pdf.BaseFont;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final TemplateEngine templateEngine;

  public String report() throws IOException {
    //  修改路径
    String path = "C:\\Users\\samin\\Desktop\\report.pdf";

    // 创建Thymeleaf上下文
    Context context = new Context();
    // 设置要传递给Thymeleaf模板的数据（例如，从后端获取的报表数据）
    context.setVariable("name", "中文标题");
    context.setVariable("content", "Content with 中文。");

    // 渲染 Thymeleaf 模板
    String htmlContent = templateEngine.process("report2PDF", context);

    ITextRenderer renderer = new ITextRenderer();
    // 配置中文字体
    ITextFontResolver fontResolver = renderer.getFontResolver();
    fontResolver.addFont("static/SimSun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    renderer.setDocumentFromString(htmlContent);
    renderer.layout();
    renderer.createPDF(Files.newOutputStream(Paths.get(path)));

    return htmlContent;
  }
}
