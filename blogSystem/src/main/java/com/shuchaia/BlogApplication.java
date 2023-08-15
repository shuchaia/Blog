package com.shuchaia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName BlogApplication
 * @Description blog前台启动类
 * @Author shuchaia
 * @Date 2023/6/26 15:50
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.shuchaia.mapper")
@EnableScheduling
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
