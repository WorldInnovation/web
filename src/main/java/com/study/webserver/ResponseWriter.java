package com.study.webserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;


public class ResponseWriter
{
	public static final Logger log = Logger.getLogger(ResponseWriter.class.getName());
	public void writeResponse(BufferedWriter out, String contentRead)
	{
		try
		{
			out.write("HTTP/1.1 200 OK");
			out.newLine();
			out.newLine();
			out.write(contentRead);
			log.info("content sent:" + contentRead);
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
