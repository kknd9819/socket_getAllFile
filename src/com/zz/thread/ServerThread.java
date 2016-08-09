package com.zz.thread;

import java.net.Socket;

public class ServerThread extends Thread {
	
	Socket socket = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
  
	@Override
	public void run() {
		// Server中的方法拷贝到此处
		
	}
	
}
