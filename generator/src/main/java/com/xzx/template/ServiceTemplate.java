package com.xzx.template;
/**
 * Service类模板
 * @author xu
 *
 */
public class ServiceTemplate {
	//Service类模板
	public static final String CLASS_TEMP = "package ${packageName}.service;\r\n\r\n"+
											"import com.alibaba.fastjson.JSONObject;\r\n"+
											"import com.sjht.park.common.util.CustomPageInfo;\r\n"+
											"import com.sjht.park.common.entity.${projectName}.${className};\r\n"+
											"public interface I${className}Service {\r\n\r\n"+
											"	int deleteByPrimaryKey(String id);\r\n\r\n"+
	    									"	public int insertSelective(${className} ${columnName});\r\n\r\n"+
										    "	public CustomPageInfo<${className}> selectByExample(JSONObject params);\r\n\r\n"+									
										    "	public ${className} selectByPrimaryKey(String id);\r\n\r\n"+
										    "	public int updateByPrimaryKey(${className} ${columnName});\r\n\r\n"+
											"}";
}
