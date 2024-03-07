package com.samin.generator.controller;

import com.samin.generator.service.EmailService;
import com.samin.generator.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final EmailService emailService;

    @GetMapping("/report")
    public String report(Model model, @RequestParam(value = "name") String name) {
        model.addAttribute("name", name);
        model.addAttribute("content", "报表内容");

        return "report2PDF";
    }

    @GetMapping("/report2PDF")
    public void report2PDF(@RequestParam(value = "email") String email) throws IOException, MessagingException {
        String html = reportService.report();
        emailService.sendEmail(email, html);
    }
}
