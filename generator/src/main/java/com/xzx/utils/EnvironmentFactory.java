package com.xzx.utils;

public class EnvironmentFactory {
	public static void createEnvironment(String projectName,String packageName,String port){
		String pom = FileUtiles.readFile("pom.xml").replace("${projectName}", projectName);
		FileUtiles.createFile(pom, projectName,"pom.xml");
		String bootstrap = FileUtiles.readFile("bootstrap.yml").replace("${projectName}", projectName).replace("${port}", port);
		FileUtiles.createFile(bootstrap, projectName+"/src/main/resources","bootstrap.yml");
		String dockerFile = FileUtiles.readFile("Dockerfile").replace("${projectName}", projectName);
		FileUtiles.createFile(dockerFile, projectName+"/src/main/docker","Dockerfile");
		FileUtiles.createFile(FileUtiles.readFile("bootstrap-dev.yml"), projectName+"/src/main/resources","bootstrap-dev.yml");	
		String basePath = "/src/main/java/";
		String path = packageName.replace(".", "/");
		FileUtiles.createFile(FileUtiles.readFile("ExceptionHandleConfig.java").replace("${packageName}", packageName), projectName+basePath+path+"/config","ExceptionHandleConfig.java");	
		//FileUtiles.createFile(FileUtiles.readFile("JsonConvertConfig.java").replace("${packageName}", packageName), projectName+basePath+path+"/config","JsonConvertConfig.java");	
		//FileUtiles.createFile(FileUtiles.readFile("SnowflakeFeignClient.java").replace("${packageName}", packageName), projectName+basePath+path+"/feign","SnowflakeFeignClient.java");	
		//FileUtiles.createFile(FileUtiles.readFile("SnowflakeHystrix.java").replace("${packageName}", packageName), projectName+basePath+path+"/feign/fallback","SnowflakeHystrix.java");	
		FileUtiles.createFile(FileUtiles.readFile("Retry.java").replace("${packageName}", packageName), projectName+basePath+path+"/feign/config","Retry.java");
	}
}
