package com.study;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static com.study.socket.Server.echoServerStart;
import static com.study.socket.Server.echoServerStop;


class ServerTest
{

	public static  int SOCKET_PORT = 3000;

	@Test
	void echoServerStartTest() throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
		echoServerStart(serverSocket);
		echoServerStop(serverSocket);
	}
}
