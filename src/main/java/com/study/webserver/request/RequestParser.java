package com.study.webserver.request;

import com.study.webserver.UtilsWebServer;

import java.io.BufferedReader;
import java.io.IOException;


public class RequestParser implements UtilsWebServer
{

	public Request parse(BufferedReader in)
	{
		Request request = new Request();
		String line = "";
		try
		{
			while ((line = in.readLine()) != null)
			{
				if (line.contains(RequestType.GET.get()))
				{
					String[] lines = line.split(SPLIT_BRAKSPACE);
					if (lines.length > 1)
					{
						request.setUrl(lines[1]);
					}
					break;
				}
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return request;
	}
}
