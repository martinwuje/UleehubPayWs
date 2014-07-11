package builder.model;

/**
 * @author Mr. klaus
 *
 * 存放数据表中一个字段的信息
 */
public class TableColumns {
	//表名称
	private String tableName;
	//数据库字段名
	private String dbColumnName;
	//属性名
	private String columnName;
	//数据库字段类型
	private String columnType;
	//java类型
	private String javaType;
	//字段长度
	private int columnSize;
	//字段小数位长度
	private int columnDecimalDigits;
	//备注
	private String remark;	
	//是否为主键
	private boolean prikey;
	//是否允许为空
	private boolean nullable;
	//是否抓取记录
	private boolean fetch;
	//抓取时使用的方法后缀
	private String methodSuffix;
	
	public TableColumns() {
		
	}
	
	public String getFirstLetterUpper(String colname){
		String f = colname.substring(0,1);
		f = f.toUpperCase();
		return f.concat(colname.substring(1));
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		String theValue=null;
		if(columnType.toUpperCase().equals("VARCHAR2")){
			theValue="string";
		}else if(columnType.toUpperCase().equals("NUMBER")){
			theValue="NUMERIC";
		}else{
			theValue=columnType;
		}
		this.columnType = theValue;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	
	public void setColumnSize(String columnSize) {
		this.columnSize = Integer.parseInt(columnSize);
	}

	public int getColumnDecimalDigits() {
		return columnDecimalDigits;
	}

	public void setColumnDecimalDigits(int columnDecimalDigits) {
		this.columnDecimalDigits = columnDecimalDigits;
	}
	
	public void setColumnDecimalDigits(String columnDecimalDigits) {
		this.columnDecimalDigits = Integer.parseInt(columnDecimalDigits);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	

	public boolean isPrikey() {
		return prikey;
	}

	public void setPrikey(boolean prikey) {
		this.prikey = prikey;
	}
	
	public void setPrikey(String prikey) {
		this.prikey = Boolean.parseBoolean(prikey);
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	public void setNullable(String nullable) {
		this.nullable = Boolean.parseBoolean(nullable);;
	}

	public String toString(){
		
		StringBuffer sb = new StringBuffer("TableColumns={\n");
		sb.append("tableName="+this.tableName+",\n");
		sb.append("dbColumnName="+this.dbColumnName+",\n");
		sb.append("columnName="+this.columnName+",\n");
		sb.append("columnType="+this.columnType+",\n");
		sb.append("javaType="+this.javaType+",\n");
		sb.append("columnSize="+this.columnSize+",\n");
		sb.append("columnDecimalDigits="+this.columnDecimalDigits+",\n");
		sb.append("remark="+this.remark.trim().replaceAll("\\n", "")+",\n");
		sb.append("prikey="+this.prikey+",\n");
		sb.append("nullable="+this.nullable+"\n");
		sb.append("}\n");
		
		return sb.toString();
	}
	

	public TableColumns(String columns) {
		String[] column = columns.split("\n");
		String[] temp =  column[1].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setTableName(temp[1]);
		
		temp =  column[2].split("=");
		if ( temp[1].indexOf(",") > -2 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setDbColumnName(temp[1]);
		
		temp =  column[3].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setColumnName(temp[1]);
		
		temp =  column[4].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setColumnType(temp[1]);
		
		temp =  column[5].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setJavaType(temp[1]);
		
		temp =  column[6].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setColumnSize(temp[1]);
		
		temp =  column[7].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setColumnDecimalDigits(temp[1]);
		
		temp =  column[8].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setRemark(temp[1]);
		
		try {
			temp =  column[9].split("=");
			if ( temp[1].indexOf(",") > -1 ) {
				temp[1] =  temp[1].substring(0, temp[1].length() - 1);
			}
			this.setPrikey(temp[1]);
			
		} catch (Exception e) {
			System.out.println(columns);
			e.printStackTrace();
		}
		
		temp =  column[10].split("=");
		if ( temp[1].indexOf(",") > -1 ) {
			temp[1] =  temp[1].substring(0, temp[1].length() - 1);
		}
		this.setNullable(temp[1]);
		
//		BeanValueUtil.setObjectInfo(this, column, "=");
	}

	public boolean isFetch() {
		return fetch;
	}

	public void setFetch(boolean fetch) {
		this.fetch = fetch;
	}

	public String getMethodSuffix() {
		return methodSuffix;
	}

	public void setMethodSuffix(String methodSuffix) {
		this.methodSuffix = methodSuffix;
	}
}
