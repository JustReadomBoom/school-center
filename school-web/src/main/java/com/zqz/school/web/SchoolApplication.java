package com.zqz.school.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: SchoolApplication
 * @Date: Created in 14:26 2022-10-31
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zqz.school.dao"})
@ComponentScan(basePackages = {"com.zqz.school"})
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }
}
