package com.shuchaia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName BlogApplication
 * @Description blog前台启动类
 * @Author shuchaia
 * @Date 2023/6/26 15:50
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.shuchaia.mapper")
// 开启定时任务
@EnableScheduling
// 启用Swagger2
@EnableSwagger2
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
