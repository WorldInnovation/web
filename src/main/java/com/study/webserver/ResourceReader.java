package com.study.webserver;

import jdk.jshell.execution.Util;

import java.io.*;
import java.util.logging.Logger;


public class ResourceReader implements UtilsWebServer
{
	private static final Logger log = Logger.getLogger(ResourceReader.class.getName());
	private static String resource;


	public ResourceReader(String resource)
	{
		this.resource = resource;
	}

	public String contentRead(String requestUri)
	{
		String line = "";
		StringBuilder stringBuilder = new StringBuilder();
		File resourceFile = new File(resource + requestUri);

		if (resourceFile.exists())
		{

			try (FileInputStream fileInputStream = new FileInputStream(resourceFile);
				  BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream))
			)
			{
				while ((line = in.readLine()) != null)
				{
					stringBuilder.append(line);
				}
			}
			catch (IOException e)
			{
				log.warning(RESOURCE_CAN_NOT_READ + requestUri);
			}
		}
		else
		{
			log.warning(FILE_NOT_FOUND);
		}
		return stringBuilder.toString();
	}
}
