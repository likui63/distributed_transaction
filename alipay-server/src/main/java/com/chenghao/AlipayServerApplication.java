package com.chenghao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.chenghao.mapper")
//定时任务的支持
@EnableScheduling
public class AlipayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlipayServerApplication.class, args);
    }

}
