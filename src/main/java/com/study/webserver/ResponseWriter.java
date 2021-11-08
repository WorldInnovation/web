package com.study.webserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ResponseWriter
{

	public void writeResponse(BufferedWriter out, String contentRead)
	{
		try
		{
			out.write("HTTP/1.1 200 OK");
			out.write("\n");
			out.write("\n");
			out.write("Hello browser");
			out.write(contentRead);
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
