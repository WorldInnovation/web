package com.study.webserver;

public class Starter
{
	public static void main(String[] args)
	{
		Server server = new Server();
		server.setPort(3000);
		server.setWebAppPath(WebServerConstants.WEB_APP_PATH);
		server.start();

	}

}
