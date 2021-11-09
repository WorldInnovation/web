package com.study.webserver.request;

import com.study.webserver.ResourceReader;
import com.study.webserver.ResponseWriter;
import com.study.webserver.WebServerConstants;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;


public class RequestHandler
{
	private final Logger log = Logger.getLogger(RequestHandler.class.getName());
	private final Socket clientSocket;
	private final ResourceReader resourceReader;
	private final ResponseWriter responseWriter;

	public RequestHandler(Socket socket, String webAppPath)
	{
		this.clientSocket = socket;
		this.responseWriter = new ResponseWriter();
		this.resourceReader = new ResourceReader(webAppPath);
	}

	public void handle()
	{
		log.info(WebServerConstants.REQUEST_HANDLER_RUN);

		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
		)
		{
			RequestParser requestParser = new RequestParser();
			Request request = requestParser.parse(in);
			log.info("before parse ------>");
			Map<Integer, String> content = resourceReader.contentRead(request.getUrl());
			log.info("content:" + content);
			responseWriter.writeResponse(out, content);
		}
		catch (IOException e)
		{
			log.info(WebServerConstants.ERROR_CLIENT_SOCKET_START + e.getMessage());
		}

	}


}
