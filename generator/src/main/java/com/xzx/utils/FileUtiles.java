package com.xzx.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FileUtiles {
	public static void createFile(String content,String path,String fileName){
		try {
			File file=new File(path);
			if(!file.exists()){//如果文件夹不存在
				file.mkdirs();//创建文件夹
			}
			OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream(path+"/"+fileName), "UTF-8");
			op.append(content);
			op.flush();
			op.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static String readFile(String fileName){
		StringBuffer sb = new StringBuffer();
		try{
			String jarPath = FileUtiles.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	        URL url=new URL("jar:" + jarPath + "files/"+fileName); 
	        InputStream is=url.openStream();
			byte[] bt = new byte[1024*1024];
			int realbyte = 0;
			while ((realbyte = is.read(bt)) > 0) {
				sb.append(new String(bt,0,realbyte,"UTF-8"));
			}
			is.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
