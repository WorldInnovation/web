package com.study.webserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;


public class RequestHandler implements Runnable, UtilsWebServer
{
	private final Logger log = Logger.getLogger(RequestHandler.class.getName());
	private final Socket clientSocket;
	private final ResourceReader resourceReader;
	private final RequestParser requestParser;
	private  final ResponseWriter responseWriter;

	public RequestHandler(Socket socket, String webAppPath)
	{
		this.clientSocket = socket;
		this.responseWriter = new ResponseWriter();
		this.resourceReader = new ResourceReader(webAppPath);
		this.requestParser = new RequestParser();
	}

	public void run()
	{
		log.info(REQUEST_HANDLER_RAN);

		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
		)
		{
			log.info(in.toString());
			Request request;
			String line = "";
			try
			{
				while ((line = in.readLine()) != null)
				{
					request = new Request();
					log.info(line);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			request = requestParser.parse(in);
			log.info(request.getUrl());
/*			log.info(request.toString());
			String contentRead = resourceReader.contentRead(request.getUrl());
			log.info(request.toString());
			responseWriter.writeResponse(out, contentRead);
			log.info(request.toString());*/

		}
		catch (IOException e)
		{
			log.info(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}

	}

}
