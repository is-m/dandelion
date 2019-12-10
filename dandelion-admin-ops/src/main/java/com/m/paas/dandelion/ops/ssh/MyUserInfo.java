package com.m.paas.dandelion.ops.ssh;

import com.jcraft.jsch.UserInfo;

public class MyUserInfo implements UserInfo {

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public boolean promptYesNo(String message) {
		return message.contains("The authenticity of host");
	}

	@Override
	public boolean promptPassword(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptPassphrase(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		return null;
	}
}
