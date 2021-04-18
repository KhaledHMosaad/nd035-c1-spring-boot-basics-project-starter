package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/result")
public class ResultController {
    @GetMapping
    public String doGet(@RequestParam(name = "isSuccessful") boolean isSuccessful,
                        @RequestParam(name = "message") String message,
                        Model model)
    {
        model.addAttribute("isSuccessful", isSuccessful);
        model.addAttribute("message", message);
        return "result";
    }
}
