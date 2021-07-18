package com.mark.wiki.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // return string
// @Controller // return page
public class TestController {
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
        return "Hello world";
    }
}
