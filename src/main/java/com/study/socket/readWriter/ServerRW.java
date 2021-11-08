package com.study.socket.readWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerRW
{
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";
	public static final int SOCKET_PORT = 3000;

	public ServerRW()
	{
	}


	public static void echoServerStart(ServerSocket serverSocket)
	{
		try (
				Socket socket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		)
		{

			System.out.println("Server start on localhost:3000");
			String echoMessage = in.readLine();
			System.out.println("Server got message:" + echoMessage);

			StringBuilder stringBuilder = new StringBuilder("echo: ${");
			stringBuilder.append(echoMessage);
			stringBuilder.append("}");
			String echoServer = stringBuilder.toString();
			out.write(echoServer, 0, echoServer.length());
			out.flush();

		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
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

	public static void main(String[] args) throws IOException
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
