package com.xzx.utils;

import java.util.List;

import com.xzx.entity.ColumnEntity;
import com.xzx.entity.TableEntity;

public class NameUtils {
	/**
	 * 生成类名或方法名(首字母大写并转驼峰)
	 * @param column
	 * @return
	 */
	public static String getMethodName(String column){
		column = column.toLowerCase();
		column = column.substring(0,1).toUpperCase()+column.substring(1);		
		return getColumnName(column);
	}
	/**
	 * 生成类的属性名称(转驼峰)
	 * @param column
	 * @return
	 */
	public static String getColumnName(String column){
		while (column.indexOf("_")!=-1) {
			int index = column.indexOf("_");
			String lower = column.substring(index+1,index+2);
			String upper = lower.toUpperCase();
			column = column.replace("_"+lower, upper);
		}
		return column;
	}
	/**
	 * 判断是否存在指定字段
	 * @param Table
	 * @param columnName
	 * @return
	 */
	public static boolean hasColumn(TableEntity Table,String columnName){
		List<ColumnEntity> columns = Table.getColumns();
		for(ColumnEntity column:columns){
			if(column.getColumnName().equalsIgnoreCase(columnName)) return true;
		}
		return false;
	}
}
