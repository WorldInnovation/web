package com.study.webserver;

import java.io.BufferedReader;
import java.io.IOException;


public class RequestParser implements UtilsWebServer
{

	public Request parse(BufferedReader bufferedReader)
	{
		Request request;
		String line = "";
		try
		{
			while ((line = bufferedReader.readLine()) != null)
			{
				request = new Request();
				requestRead(line, request);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private void requestRead(String line, Request request)
	{
		if (line.contains(RequestType.GET.get()))
		{
			String[] lines = line.split(SPLIT_BRAKSPACE);
			request.setUrl(lines[1]);
		}
	}




}
