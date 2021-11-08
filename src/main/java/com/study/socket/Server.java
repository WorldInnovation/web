package com.study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class Server

{
	public static final String ERROR_ECHO_SERVER_MESSAGE = "IOError in echo server:";
	public static final int BUFFER_CAPACITY = 2048;
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";
	public static final int SOCKET_PORT = 3000;


	public static void echoServerStart(ServerSocket serverSocket)
	{
		try (
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
		)
		{
			byte[] buffer = new byte[BUFFER_CAPACITY];
			int count = inputStream.read(buffer);

			StringBuilder stringBuilder = new StringBuilder("echo: ${");

			char[] echoChar = new char[BUFFER_CAPACITY];
			for (int i = 0; i < count; i++)
			{
				echoChar[i] = (char) buffer[i];
			}

			String echoMessage = new String(echoChar, 0, count);
			stringBuilder.append(echoMessage);
			stringBuilder.append("}");
			String echoServerResponse = stringBuilder.toString();

			outputStream.write(echoServerResponse.getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException e)
		{
			System.out.println(ERROR_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}

	public static void echoServerStop(ServerSocket serverSocket)
	{
		try
		{
			serverSocket.close();
		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}


	public static void main(String[] args)
	{
		try (ServerSocket serverSocket = new ServerSocket(SOCKET_PORT))
		{
			echoServerStart(serverSocket);
			echoServerStop(serverSocket);
		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}
}
