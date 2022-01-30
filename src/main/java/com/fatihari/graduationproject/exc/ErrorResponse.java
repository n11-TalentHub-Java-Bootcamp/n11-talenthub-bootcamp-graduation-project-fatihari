package com.fatihari.graduationproject.exc;

public class ErrorResponse {

	private int status;
	private String message;
	private long timeStamp;
	private String detail;
	
	public ErrorResponse() {
		
	}

	public ErrorResponse(int status, String message, long timeStamp, String detail) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}







