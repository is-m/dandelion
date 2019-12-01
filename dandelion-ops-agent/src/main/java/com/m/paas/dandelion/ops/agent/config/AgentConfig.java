package com.m.paas.dandelion.ops.agent.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AgentConfig {

	private static final Logger log = LoggerFactory.getLogger(AgentConfig.class);

	private static AgentConfig instance = new AgentConfig();
	private Properties properties = null;

	private AgentConfig() {
		this.init();
	}

	private void init() {
		try {
			properties = new Properties();
			properties.load(AgentConfig.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			log.error("load application.properties error", e);
			Runtime.getRuntime().exit(1);
		}
	}

	public static AgentConfig getInstance() {
		return instance;
	}

	public String getServers() {
		return properties.getProperty("master.servers");
	}

}
