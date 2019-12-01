package com.m.paas.dandelion.admin.ops.vo;

import java.io.Serializable;

public class WebSocketResult implements Serializable {

	private static final long serialVersionUID = 8294118824878973959L;

	private String type;

	/**
	 * 1 成功
	 */
	private int status = 1;

	private Object data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public WebSocketResult(String type, int status, Object data) {
		this.type = type;
		this.status = status;
		this.data = data;
	}

	public static WebSocketResult success(String type, Object data) {
		return new WebSocketResult(type, 1, data);
	}
	
	public static WebSocketResult error(String type, Object data) {
		return new WebSocketResult(type, 0, data);
	}

}
