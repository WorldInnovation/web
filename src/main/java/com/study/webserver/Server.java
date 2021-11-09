package com.study.webserver;

import com.study.webserver.request.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{
	//public final Logger log = LoggerFactory.getLogger(getClass().getName());
	private int port;
	private String webAppPath;

	public Server(int port, String webAppPath)
	{
		this.port = port;
		this.webAppPath = webAppPath;
	}

	public Server()
	{
	}


	public void start()
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
		//	log.info(WebServerConstants.TWELVE_STARS + WebServerConstants.SERVER_START_ON_HOST + port + WebServerConstants.TWELVE_STARS);
			while (true)
			{
				Socket socket = serverSocket.accept();
				//	log.info(WebServerConstants.SERVER_GOT_CONNECTION + socket.getInetAddress().getHostAddress());

					RequestHandler requestHandler = new RequestHandler(socket, webAppPath);
					requestHandler.handle();

				//	log.info(WebServerConstants.SERVER_GOT_CONNECTION + socket.getInetAddress().getCanonicalHostName());
			}
		}
		catch (IOException e)
		{
			//log.info(WebServerConstants.UNKNOWN_HOST_ERROR, e);
		}

	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getWebAppPath()
	{
		return webAppPath;
	}

	public void setWebAppPath(String webAppPath)
	{
		this.webAppPath = webAppPath;
	}

}

