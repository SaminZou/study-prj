package com.samin.generator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReportController {

    @GetMapping("/report")
    public String report(Model model, @RequestParam(value = "name") String name) {
        model.addAttribute("name", name);
        model.addAttribute("content", "报表内容");

        return "reportTemplate";
    }
}
