package com.m.paas.dandelion.ops.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.m.paas.dandelion.ops.mapper")
public class ModuleConfig {

}
