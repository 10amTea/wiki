package com.mark.wiki.Controller;

import com.mark.wiki.domain.Demo;
import com.mark.wiki.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // return string
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/list")
    public List<Demo> list(String name) {
        return demoService.list();
    }
}
