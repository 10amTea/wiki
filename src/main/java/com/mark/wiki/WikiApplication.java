package com.mark.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WikiApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {
//		SpringApplication.run(WikiApplication.class, args);
        SpringApplication app = new SpringApplication(WikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOGGER.info("启动成功！！");
        LOGGER.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
