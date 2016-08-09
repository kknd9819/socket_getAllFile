package com.zz.server;

import java.io.IOException; 
import java.net.ServerSocket;
import java.net.Socket;

import com.zz.thread.ServerThread;

public class Server {
	public static void main(String[] args)  {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			Socket socket = null;
			System.out.println("***** 服务已经开启,等待客户端连接 *****");
			while(true){
				socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
