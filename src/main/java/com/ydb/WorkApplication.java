package com.ydb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ydb.dao")
@EnableSwagger2
public class WorkApplication {


	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}
}
