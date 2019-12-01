package com.m.paas.dandelion.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ruoyi", "com.m.paas.dandelion.admin" })
public class DandelionAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DandelionAdminServerApplication.class, args);
	}
}
