package com.xzx.template;

public class ApplicatinTemplate{

	//Applicatin类模板
	public static final String CLASS_TEMP = "package ${packageName};\r\n"+
											"import org.springframework.boot.SpringApplication;\r\n"+
											"import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n"+
											"import org.springframework.cloud.netflix.eureka.EnableEurekaClient;\r\n"+
											"import org.springframework.cloud.netflix.feign.EnableFeignClients;\r\n"+
											"import tk.mybatis.spring.annotation.MapperScan;\r\n\r\n"+


											"@EnableEurekaClient\r\n"+
											"@EnableFeignClients\r\n"+
											"@SpringBootApplication\r\n"+
											"@MapperScan(\"${packageName}.mapper\")\r\n"+
											"public class ${className}Application {\r\n"+

											"	public static void main(String[] args) {\r\n"+

											"		SpringApplication.run(${className}Application.class, args);\r\n"+
											"	}\r\n"+
											"}\r\n";
}
