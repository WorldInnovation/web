package com.study.socket.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerThread implements UtilsSocketThread
{
	public static void main(String[] args)
	{
		try (
				ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
		)
		{
			System.out.println("Server start on localhost:3000");
			ExecutorService pool = Executors.newFixedThreadPool(3);
			while (true)
			{
				Socket socket = serverSocket.accept();
				{
					System.out.println("Server have got connection:" + socket.getInetAddress().getHostAddress());
					ClientHandler clientSocket = new ClientHandler(socket);
					pool.execute(clientSocket);
					System.out.println(socket.getInetAddress().getCanonicalHostName());
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(UNKNOWN_HOST_ERROR + e.getMessage());
		}
	}

}

