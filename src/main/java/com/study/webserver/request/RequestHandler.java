package com.study.webserver.request;

import com.study.webserver.ResourceReader;
import com.study.webserver.ResponseWriter;
import com.study.webserver.UtilsWebServer;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;


public class RequestHandler implements UtilsWebServer
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
		log.info(REQUEST_HANDLER_RAN);

		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
		)
		{
			RequestParser requestParser = new RequestParser();
			Request request = requestParser.parse(in);
			log.info("before parse ------>");
			String content = resourceReader.contentRead(request.getUrl());
			log.info("content:" + content);
			responseWriter.writeResponse(out, content);
		}
		catch (IOException e)
		{
			log.info(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}

	}


}
