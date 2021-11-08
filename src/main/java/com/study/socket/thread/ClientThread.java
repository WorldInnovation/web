package com.study.socket.thread;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientThread implements UtilsSocketThread
{

	public static void main(String[] args)
	{
		try (
				Socket socket = new Socket(SOCKET_HOST, SOCKET_PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		)
		{
			String clientMessage = null;
			while (!"exit".equalsIgnoreCase(clientMessage))
			{
				System.out.println("Enter message for echo server or - exit for escape, please:");
				clientMessage = reader.readLine();
				out.write(clientMessage, 0, clientMessage.length());//send
				out.newLine();
				out.flush();
				String responseMessage = in.readLine();
				System.out.println("Response from server:" + responseMessage);
			}
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
}
