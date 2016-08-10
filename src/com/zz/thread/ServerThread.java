package com.zz.thread;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	
	Socket socket = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
  
	@Override
	public void run() {
		// Server中的方法拷贝到此处
		try {
			String path = socket.getInetAddress().toString();
			File file = new File("e:\\" + path);
			if(!file.exists()){
				file.mkdir();
			}
			FileInputStream in = (FileInputStream) socket.getInputStream();
			FileOutputStream out = new FileOutputStream("e:\\" + path);
			byte[] b = new byte[4096];
			int a = 0;
			while((a=in.read(b, 0, b.length))!=-1){
				out.write(b, 0, a);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
