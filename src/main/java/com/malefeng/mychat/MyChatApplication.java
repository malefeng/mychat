package com.malefeng.mychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.malefeng.mychat.dao.mapper")
public class MyChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyChatApplication.class, args);
    }

}
