package com.shuchaia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @ClassName BlogApplication
 * @Description blog后台启动类
 * @Author shuchaia
 * @Date 2023/7/11 21:28
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.shuchaia.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
