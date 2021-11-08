package com.study;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static com.study.socket.Client.echoClientStart;
import static com.study.socket.Client.echoClientStop;


class ClientTest
{

	private static final String SOCKET_HOST = "localhost";
	private static final int SOCKET_PORT = 3000;

	@Test
	void echoClientStartTest() throws IOException
	{
		Socket socket = new Socket(SOCKET_HOST, SOCKET_PORT);
		echoClientStart(socket);
		echoClientStop(socket);
	}
}
