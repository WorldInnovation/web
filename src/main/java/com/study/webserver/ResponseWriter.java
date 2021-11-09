package com.study.webserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;


public class ResponseWriter
{
	public static final Logger log = Logger.getLogger(ResponseWriter.class.getName());

	public void writeResponse(BufferedWriter out, Map<Integer, String> contentRead)
	{
		String firstResponseString = "";
		String content = "";
		try
		{
			if (contentRead.containsKey(200))
			{
				firstResponseString = "HTTP/1.1 200 OK";
				content = contentRead.get(200);
				log.info("content sent 200:" + contentRead.get(200));
			}

			else if (contentRead.containsKey(404))
			{
				firstResponseString = "HTTP/1.1 404 Not Found";
				content = contentRead.get(404);
				log.info("content sent 404:" + contentRead.get(404));
			}
			else if (contentRead.containsKey(500))
			{
				firstResponseString = "HTTP/1.1 404 Not Found";
				content = contentRead.get(500);
				log.info("content sent 404:" + contentRead.get(500));
			}

			out.write(firstResponseString);
			out.write(WebServerConstants.LINE_SEPARATOR);///r/n
			out.write(WebServerConstants.LINE_SEPARATOR);///r/n
			out.write(content);

		}
		catch (IOException e)
		{
			log.warning(WebServerConstants.SERVER_ERROR_SEND_RESPONSE);
		}

	}
}
