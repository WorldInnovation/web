package com.study.webserver;

public class Starter implements UtilsWebServer
{
	public static void main(String[] args)
	{
		Server server = new Server();
		server.setPort(SOCKET_PORT);
		server.setWebAppPath(WEB_APP_PATH);
		server.start();

	}

}
