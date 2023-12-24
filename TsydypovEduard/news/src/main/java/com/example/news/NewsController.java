package com.example.news;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title","Цыдыпов Эдуард");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница про Эдуарда");
        return "about";
    }
}
