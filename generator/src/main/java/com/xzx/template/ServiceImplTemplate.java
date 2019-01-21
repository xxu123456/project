package com.xzx.template;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * ServiceImpl类模板
 * @author xu
 *
 */
public class ServiceImplTemplate {
	//ServiceImpl类模板
	public static final String CLASS_TEMP = "package ${packageName}.service.impl;\r\n\r\n"+
											"import com.alibaba.fastjson.JSONObject;\r\n"+
											"import com.sjht.park.common.util.ExampleUtils;\r\n"+
											"import com.sjht.park.common.entity.${projectName}.${className};\r\n"+
											"import org.slf4j.Logger;\r\n"+
											"import org.slf4j.LoggerFactory;\r\n"+
											"import ${packageName}.mapper.${className}Mapper;\r\n"+
											"import ${packageName}.service.I${className}Service;\r\n"+
											"import com.sjht.park.common.util.CustomPageInfo;\r\n"+
											"import com.sjht.park.common.util.HelperUtils;\r\n"+
											"import org.springframework.beans.factory.annotation.Autowired;\r\n"+
											"import org.springframework.stereotype.Service;\r\n"+
											"import tk.mybatis.mapper.entity.Example;\r\n"+
											"import java.util.Date;\r\n"+
											"import java.util.List;\r\n\r\n"+
										
											"@Service\r\n"+
											"public class ${className}ServiceImpl implements I${className}Service {\r\n"+
											"	private final Logger logger = LoggerFactory.getLogger(this.getClass());\r\n"+
	    									"	@Autowired\r\n"+
	    									"	private ${className}Mapper ${columnName}Mapper;\r\n"+
	    									
	    									"	/**\r\n"+
											"	*  根据id删除${tableComment}记录\r\n"+
											"	 *  @param id\r\n"+
											"	 *  @return\r\n"+
											"	 */\r\n"+
											"	@Override\r\n"+
											"	public int deleteByPrimaryKey(String id) {\r\n"+
											"${delete}"+
											"	}\r\n"+
									
											"	/**\r\n"+
											"	 *  添加一条${tableComment}记录\r\n"+
											"	 * @param ${columnName}\r\n"+
											"	 * @return\r\n"+
											"	 */\r\n"+
											"	@Override\r\n"+
											"	public int insertSelective(${className} ${columnName}) {\r\n"+
											"	    //字段非空验证\r\n"+
											"${noyNull}"+
											"		${columnName}.setId(HelperUtils.getUuid());\r\n"+
											"${setStatus}"+
											"${addTime}"+
											"${lastOpTime}"+
											"	    return ${columnName}Mapper.insertSelective(${columnName});\r\n"+
											"	}\r\n"+
									
											"	/**\r\n"+
											"	 *  根据条件查询${tableComment}记录\r\n"+
											"	 * @param params\r\n"+
											"	 * @return\r\n"+
											"	 */\r\n"+
											"	@Override\r\n"+
											"	public CustomPageInfo<${className}> selectByExample(JSONObject params) {\r\n"+
											"	    HelperUtils.disposePage(params,true);\r\n"+
											"	    Example example = ExampleUtils.initExample(${className}.class,params);\r\n"+
											"${notStatus}"+
											"	    List<${className}> list =  ${columnName}Mapper.selectByExample(example);\r\n"+
											"	    return new CustomPageInfo<>(list);\r\n"+
											"	}\r\n"+
									
											"	/**\r\n"+
											"	 *  查询${tableComment}记录\r\n"+
											"	 * @param id\r\n"+
											"	 * @return\r\n"+
											"	 */\r\n"+
											"	@Override\r\n"+
											"	public ${className} selectByPrimaryKey(String id) {\r\n"+
											"	    return ${columnName}Mapper.selectByPrimaryKey(id);\r\n"+
											"	}\r\n"+
									
											"	/**\r\n"+
											"	 * 根据id更新一条${tableComment}记录\r\n"+
											"	 * @param ${columnName}\r\n"+
											"	 * @return\r\n"+
											"	 */\r\n"+
											"	@Override\r\n"+
											"	public int updateByPrimaryKey(${className} ${columnName}) {\r\n"+
											"${lastOpTime}"+
											"	    return ${columnName}Mapper.updateByPrimaryKeySelective(${columnName});\r\n"+
											"	}\r\n"+
											"}\r\n";
	//设置最后操时间
	public static final String LAST_OP_TIME = "	    ${columnName}.setLastOpTime(new Date());\r\n";
	
	//设置添加时间
	public static final String ADD_TIME = "	    ${columnName}.setAddTime(new Date());\r\n";
		
	//逻辑删除
	public static final String DEL_STATUS = "	    ${className} ${columnName} = new ${className}();\r\n"+
											"	    ${columnName}.setId(id);\r\n"+
											"	    ${columnName}.setStatus(9);//逻辑删除\r\n"+
											"	    return ${columnName}Mapper.updateByPrimaryKeySelective(${columnName});\r\n";
	
	//设置状态初始化值
	public static final String SET_STATUS = "		${columnName}.setStatus(0);\r\n";
	
	//设置状态初始化值
	public static final String NOT_STATUS = "	    example.and().andNotEqualTo(\"status\",9);\r\n";
	
	//物理删除
	public static final String DELETE = "	    return ${columnName}Mapper.deleteByPrimaryKey(id);\r\n";
	
	//字段非空验证备注
	public static final String NOT_NULL = "	    HelperUtils.notNull(${columnName}.get${mehtodName}(),\"${columnComment}\");\r\n";
	
}
