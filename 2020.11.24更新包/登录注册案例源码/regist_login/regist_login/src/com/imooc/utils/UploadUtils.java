package com.imooc.utils;

import java.util.UUID;

public class UploadUtils {
	
	public static String getUuidFileName(String fileName){
		// 解决文件重名的问题:   
		// a.jpg  -- 获得后缀名.jpg -- 生成一段随机字符串将a替换掉 xxdfwerw.jpg
		int idx = fileName.lastIndexOf(".");
		String exName = fileName.substring(idx);// .jpg
		// 生成随机字符串:
		String uuidFileName = UUID.randomUUID().toString().replace("-", "")+exName;
	
		return uuidFileName;
	}
	
	public static void main(String[] args) {
		System.out.println(UploadUtils.getUuidFileName("a.jpg"));
	}
}
