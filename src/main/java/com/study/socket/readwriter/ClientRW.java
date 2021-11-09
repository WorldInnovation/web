package com.study.socket.readwriter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientRW
{
	public static final String ERROR_CLIENT_SOCKET_START = "Error client socket start:";
	public static final String ERROR_STOP_ECHO_SERVER_MESSAGE = "IOError when close echo server:";
	public static final String UNKNOWN_HOST_ERROR = " Unknown host error:";
	public static final String SOCKET_HOST = "localhost";
	public static final int SOCKET_PORT = 3000;

	public ClientRW()
	{
	}

	public static void echoClientStart(Socket socket)
	{
		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		)
		{

			System.out.println("Enter message for echo server, please:");
			String clientMessage = reader.readLine();

			out.write(clientMessage, 0, clientMessage.length());//send
			out.newLine();
			out.flush();
			String responseMessage = in.readLine();
			System.out.println("Response from server:" + responseMessage);
		}
		catch (UnknownHostException e)
		{
			System.out.println(UNKNOWN_HOST_ERROR + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}
	}

	public static void echoClientStop(Socket socket)
	{
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			System.out.println(ERROR_STOP_ECHO_SERVER_MESSAGE + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException
	{
		Socket socketServer = new Socket(SOCKET_HOST, SOCKET_PORT);
		echoClientStart(socketServer);
		echoClientStop(socketServer);
	}
}
