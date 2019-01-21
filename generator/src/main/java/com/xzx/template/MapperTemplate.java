package com.xzx.template;
/**
 * mapper类模板
 * @author xu
 *
 */
public class MapperTemplate {
	//mapper类模板
	public static final String CLASS_TEMP = "package ${packageName}.mapper;\r\n\r\n"+
											"import com.sjht.park.common.mybatis.TKMapper;\r\n"+
											"import com.sjht.park.common.entity.${projectName}.${className};\r\n"+
											"public interface ${className}Mapper extends TKMapper<${className}> {\r\n"+
											"\r\n}\r\n";
}
