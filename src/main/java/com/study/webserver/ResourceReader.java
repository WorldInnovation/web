package com.study.webserver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class ResourceReader
{
	private static final Logger log = Logger.getLogger(ResourceReader.class.getName());
	private String resource;


	public ResourceReader(String resource)
	{
		this.resource = resource;
	}

	public Map<Integer, String> contentRead(String requestUri)
	{
		String line = "";
		StringBuilder stringBuilder = new StringBuilder();
		Map<Integer, String> contentStatusMap = new HashMap<>();
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
				contentStatusMap.put(200, stringBuilder.toString());
			}
			catch (IOException e)
			{
				log.warning(WebServerConstants.RESOURCE_CAN_NOT_READ + requestUri);
				stringBuilder.append(WebServerConstants.SERVER_ERROR_SEND_RESPONSE + requestUri);
				contentStatusMap.put(500, stringBuilder.toString());
			}
		}
		else
		{
			log.warning(WebServerConstants.FILE_NOT_FOUND);
			stringBuilder.append(WebServerConstants.FILE_NOT_FOUND + requestUri);
			contentStatusMap.put(404, stringBuilder.toString());
		}
		return contentStatusMap;
	}
}
