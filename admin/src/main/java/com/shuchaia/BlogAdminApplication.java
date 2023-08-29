package com.shuchaia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName BlogApplication
 * @Description blog后台启动类
 * @Author shuchaia
 * @Date 2023/7/11 21:28
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.shuchaia.mapper")
// 启动Swagger2
@EnableSwagger2
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
