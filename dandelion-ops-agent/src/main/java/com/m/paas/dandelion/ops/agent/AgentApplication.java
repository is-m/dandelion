package com.m.paas.dandelion.ops.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.m.paas.dandelion.ops.agent.config.AgentConfig;

public class AgentApplication {

	private static final Logger log = LoggerFactory.getLogger(AgentApplication.class);

	public static void main(String[] args) {
		log.info("agent starting");
		String servers = AgentConfig.getInstance().getServers();
		log.info("agent start success");

		log.info("masters:" + servers);
	}
}
