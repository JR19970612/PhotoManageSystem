package com.ydb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ydb.dao")
public class WorkApplication {


	public static void main(String[] args) {
		SpringApplication.run(WorkApplication.class, args);
	}
}
