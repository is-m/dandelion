package com.m.paas.dandelion.admin.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DispacherController {

	private static final Logger log = LoggerFactory.getLogger(DispacherController.class);

	@GetMapping("/page/**")
	public String dispach(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String templatePageUrl = uri.substring(uri.indexOf("/page/") + 6);

		log.info("redirect to static page for {}", templatePageUrl);
		return templatePageUrl;
	}
}
