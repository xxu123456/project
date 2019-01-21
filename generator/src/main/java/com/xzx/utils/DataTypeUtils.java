package com.xzx.utils;

import java.util.HashMap;
import java.util.Map;

public class DataTypeUtils {
	private static Map<String,String> types = new HashMap<String,String>();
	private static Map<String,String> imports = new HashMap<String,String>();
	static{
		types.put("INT","Integer");
		types.put("VARCHAR","String");
		types.put("VARBINARY","String");
		types.put("CHAR","String");
		types.put("BLOB","byte[]");
		types.put("TEXT","String");
		types.put("INTEGER","Long");
		types.put("TINYINT","Integer");
		types.put("SMALLINT","Integer");
		types.put("MEDIUMINT","Integer");
		types.put("BIT","Boolean");
		types.put("BIGINT","Long");
		types.put("FLOAT","Float");
		types.put("DOUBLE","Double");
		types.put("DECIMAL","BigDecimal");
		types.put("BOOLEAN","Integer");
		types.put("ID","Long");
		types.put("DATE","Date");
		types.put("TIME","Date");
		types.put("DATETIME","Date");
		types.put("TIMESTAMP","Date");
		types.put("YEAR","Date");
		
		imports.put("DECIMAL", "import java.math.BigDecimal;\r\n");
		imports.put("DATE", "import java.util.Date;\r\n");
		imports.put("TIME", "import java.util.Date;\r\n");
		imports.put("DATETIME", "import java.util.Date;\r\n");
		imports.put("TIMESTAMP", "import java.util.Date;\r\n");
		imports.put("YEAR", "import java.util.Date;\r\n");
	}
	public static String getType(String jdbcType){
		return types.get(jdbcType);
	}
	public static String getImport(String jdbcType){
		return imports.get(jdbcType)==null?"":imports.get(jdbcType);
	}
}
