/**
 * 
 */
package com.uc.system.model;

/**
 * @author Simple
 * 返回操作信息
 *
 */
public class Message {
	private String message; //返回信息
	private boolean state;//返回的状态
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
