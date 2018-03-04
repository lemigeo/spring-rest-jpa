package com.rest.data;

public class RestResponse {
	private Boolean success;
	private String response;
	
	public RestResponse() {		
	}
	
	public RestResponse(Boolean success, String response) {
		this.success = success;
		this.setResponse(response);
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
