package com.study.webserver;

import com.study.webserver.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class Server implements UtilsWebServer
{
	private final Logger log = Logger.getLogger(Server.class.getName());
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

	public void start()
	{
		try (ServerSocket serverSocket = new ServerSocket(SOCKET_PORT))
		{
			log.info(TWELVE_STARS + SERVER_START_ON_HOST + SOCKET_PORT + TWELVE_STARS);
			while (true)
			{
				Socket socket = serverSocket.accept();
				{
					log.info(SERVER_GOT_CONNECTION + socket.getInetAddress().getHostAddress());

					RequestHandler requestHandler = new RequestHandler(socket, webAppPath);
					requestHandler.handle();

					log.info(SERVER_GOT_CONNECTION + socket.getInetAddress().getCanonicalHostName());
				}
			}
		}
		catch (IOException e)
		{
			log.info(UNKNOWN_HOST_ERROR + e.getMessage());
		}

	}


}

