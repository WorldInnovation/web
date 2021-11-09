package com.study.webserver.request;

import java.util.function.Supplier;


public enum RequestType implements Supplier<String>
{
	GET("GET");

	private String requestType;

	RequestType(String requestType)
	{
		this.requestType = requestType;
	}

	@Override
	public String get()
	{
		return requestType;
	}
}
