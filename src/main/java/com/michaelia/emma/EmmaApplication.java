package com.michaelia.emma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) // 禁用SpringSecurity
@MapperScan(basePackages= "com.michaelia.emma.dao.*")
@ServletComponentScan
public class EmmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmmaApplication.class, args);
    }

}
