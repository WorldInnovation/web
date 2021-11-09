package com.study.webserver.request;

import com.study.webserver.WebServerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;


public class RequestParser
{
	Logger log = LoggerFactory.getLogger(getClass().getName());
	public Request parse(BufferedReader in)
	{
		Request request = new Request();
		String line = "";
		try
		{
			while ((line = in.readLine()) != null)
			{
				if (line.contains("GET"))
				{
					String[] lines = line.split(WebServerConstants.SPLIT_BRAKSPACE);
					if (lines.length > 1)
					{
						request.setUrl(lines[1]);
					}
					break;
				}
				else
				{

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
