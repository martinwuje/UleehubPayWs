package builder.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import builder.model.TableColumns;

/**
 * @author Mr. klaus
 *
 * 连接数据库
 */
public class DBUtil {
	private static Connection conn;
	private static String dbuser;
	
	static {
		try{   
			Properties varp = PropertiesUtil.getDatebaseProperties();
			dbuser = varp.getProperty("dataSource.username");
			Class.forName(varp.getProperty("dataSource.driverClassName"));
			conn = DriverManager.getConnection(varp.getProperty("dataSource.url"),varp.getProperty("dataSource.username"),varp.getProperty("dataSource.password"));
	    }catch (ClassNotFoundException e){
	    	System.out.println(e.toString());
	    }catch (SQLException e){
	    	System.out.println(e.toString());
	    } 
	}
	
	/**
	 * 得到数据库表信息
	 * @param tablename 表名
	 * @param needSchema 是否需要匹配模式
	 *  注：这个参数主要是因为在使用oracle时，不同用户下有相同表时，会查出所有表的属性，
	 *  为true时，将使用表名的用户进行匹配，过滤掉其他表，一般置为false就行了。
	 * @return 返回表的每个字段的属性 列表
	 */
	public static List<TableColumns> getDbTableInfo(String tablename,boolean needSchema){
		List<TableColumns> tcList = new ArrayList<TableColumns>();
		try {
			DatabaseMetaData dmd = conn.getMetaData();   		
			//要获得表所在的编目。串“""”意味着没有任何编目，Null表示所有编目。
			String catalog = null;
			//要获得表所在的模式。串“""”意味着没有任何模式，Null表示所有模式。该参数可以包含单字符的通配符（“_”）,也可以包含多字符的通配符（“%”）。
			String schema = null;
			if(needSchema){
				schema = dbuser;
			}
			//指出要返回表名与该参数匹配的那些表，该参数可以包含单字符的通配符（“_”）,也可以包含多字符的通配符（“%”）。
			String tableName = tablename.toUpperCase();
			
			String prikeyCol = null;
			ResultSet prikey = dmd.getPrimaryKeys(catalog, schema, tableName);
			if(prikey.next()){
				prikeyCol = prikey.getString("COLUMN_NAME");
			}
			ResultSet columns = dmd.getColumns(catalog,schema,tableName,null);
			TableColumns tc = null;
			while(columns.next()){
				tc = new TableColumns();
				tc.setTableName(tableName);
				if(prikeyCol.equals(columns.getString("COLUMN_NAME"))){
					tc.setPrikey(true);
				}
				tc.setDbColumnName(columns.getString("COLUMN_NAME"));
				tc.setColumnName(columns.getString("COLUMN_NAME").toLowerCase());
				tc.setColumnType(columns.getString("TYPE_NAME"));
//				System.out.println(columns.getString("COLUMN_NAME")+" "+columns.getString("TYPE_NAME"));
				if(tc.getColumnType().equalsIgnoreCase("DECIMAL")){
					tc.setJavaType("Float");  
				}
				else if(tc.getColumnType().equalsIgnoreCase("NUMBER")
						||tc.getColumnType().equalsIgnoreCase("INTEGER") || tc.getColumnType().equalsIgnoreCase("bigint")
						|| tc.getColumnType().equalsIgnoreCase("int")
						|| tc.getColumnType().equalsIgnoreCase("bigint identity")
						|| tc.getColumnType().equalsIgnoreCase("smallint")
						|| tc.getColumnType().equalsIgnoreCase("numeric")){
					tc.setJavaType("Integer");
				}else if(tc.getColumnType().equalsIgnoreCase("VARCHAR2")
						||tc.getColumnType().equalsIgnoreCase("VARCHAR")
						|| tc.getColumnType().equalsIgnoreCase("text")){
					tc.setJavaType("String");
				}else if(tc.getColumnType().equalsIgnoreCase("DATE")||tc.getColumnType().equalsIgnoreCase("DATETIME")
						||tc.getColumnType().equalsIgnoreCase("TIMESTAMP")){
					tc.setJavaType("Date");
				}else if(tc.getColumnType().equalsIgnoreCase("bit")){
					tc.setJavaType("boolean");
				}else{
					tc.setJavaType("String");
				}
				tc.setColumnSize(columns.getInt("COLUMN_SIZE"));
				tc.setColumnDecimalDigits(columns.getInt("DECIMAL_DIGITS"));
				tc.setRemark(columns.getString("REMARKS"));
				if("YES".equalsIgnoreCase(columns.getString("IS_NULLABLE"))){
					tc.setNullable(true);
				}else{
					tc.setNullable(false);
				}
				tcList.add(tc);
			}

		} catch (SQLException e) {
			System.out.println("读取表结构出现异常");
			e.printStackTrace();
		}
		return tcList;
	}
}