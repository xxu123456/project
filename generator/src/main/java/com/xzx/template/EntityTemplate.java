package com.xzx.template;
/**
 * 类模板
 * @author xu
 *
 */
public class EntityTemplate {
	//类模板
	public static final String CLASS_TEMP = "package com.sjht.park.common.entity.${projectName};\r\n\r\n"+
											"import javax.persistence.Id;\r\n"+
											"import javax.persistence.Table;\r\n"+
											"${imports}\r\n\r\n"+
											"/**\r\n* ${classComment}\r\n* @author xu\r\n*/\r\n@Table\r\n"+
											"public class ${className}{\r\n"+
											"${columns}\r\n"+
											"${methods}\r\n"+
											"}\r\n";
	//字段模板
	public static final String COLUMN_TEMP = "	private ${dataType} ${columnName}; //${columnComment}\r\n\r\n";
	//方法模板
	public static final String METHOD_TEMP = "	public ${dataType} get${methodName}(){\r\n"+
											 "		return ${columnName};\r\n"+
											 "	}\r\n"+
											 "	public void set${methodName}(${dataType} ${columnName}){\r\n"+
											 "		this.${columnName} = ${columnName};\r\n"+
											 "	}\r\n";

}
