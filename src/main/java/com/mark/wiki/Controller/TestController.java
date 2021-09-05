package com.mark.wiki.Controller;

import com.mark.wiki.domain.Test;
import com.mark.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // return string
// @Controller // return page
public class TestController {

    @Value("${test.hello:TEST}")
    private String testHello;

    @Autowired
    private TestService testService;

    /**
     * GET, POST, PUT, DELETE
     * <p>
     * /user?id=1 traditional style
     * /user/1 restful style
     *
     * @return string
     */
//    @RequestMapping("/hello") // All ways can be accessed.
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post, " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(String name) {
        return testService.list();
    }
}
