package com.study.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.study.socket.thread.UtilsSocketThread.ERROR_CLIENT_SOCKET_START;


public class ClientHandler implements Runnable
{
	private final Socket clientSocket;

	public ClientHandler(Socket socket)
	{
		this.clientSocket = socket;
	}

	public void run()
	{
		System.out.println("Client handler run on server");

		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		)
		{
			String echoMessage;
			while ((echoMessage = in.readLine()) != null)
			{
				System.out.println("Client handler got message:" + echoMessage);
				StringBuilder stringBuilder = new StringBuilder("echo: ${");
				stringBuilder.append(echoMessage);
				stringBuilder.append("}");
				String echoServer = stringBuilder.toString();
				out.println(echoServer);
			}

		}
		catch (IOException e)
		{
			System.out.println(ERROR_CLIENT_SOCKET_START + e.getMessage());
		}

	}

}
