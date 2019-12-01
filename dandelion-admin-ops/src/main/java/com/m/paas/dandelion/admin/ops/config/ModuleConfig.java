package com.m.paas.dandelion.admin.ops.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.m.paas.dandelion.admin.ops.server.mapper")
public class ModuleConfig {

}
