package com.study.webserver.request;

import java.util.Map;


public class Request
{
	private String url;
	private Map<String, String> headers;
	private HttpMethod requestType;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Map<String, String> getHeaders()
	{
		return headers;
	}

	public void setHeaders(Map<String, String> headers)
	{
		this.headers = headers;
	}

	public HttpMethod getRequestType()
	{
		return requestType;
	}

	public void setRequestType(HttpMethod requestType)
	{
		this.requestType = requestType;
	}

	@Override
	public String toString()
	{
		return "Request{" +
				"url='" + url + '\'' +
				", headers=" + headers +
				", requestType=" + requestType +
				'}';
	}
}
