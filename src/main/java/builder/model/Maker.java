package builder.model;

import java.util.HashMap;

public class Maker {
	//模板路径
	private String templetPath;
	//模板路径
	private String basePath;
	//创建者
	private String author;
	//包名称
	private String packageName;
	//文件存放路径
	private String createPath;
	//表名称
	private String tableName;
	//表别名
	private String tableAliasName;
	//实体名[生成的所有文件都与该名匹配]
	private String entityName;
	//实体描述
	private String entityDesc;
	//Spring配置文件
//	private HashMap spring;
	//数据库操作配置文件
//	private HashMap sql;
	//框架配置文件
//	private HashMap frames;
	//JSP文件夹
//	private  HashMap jsp;
	//表关系
	private HashMap relations;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEntityDesc() {
		return entityDesc;
	}
	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
		this.createPath = getBasePath() + packageName.replaceAll("\\.", "/");
	}
	public String getTableAliasName() {
		return tableAliasName;
	}
	public void setTableAliasName(String tableAliasName) {
		this.tableAliasName = tableAliasName;
	}
	public String getTableName() {
		return tableName;
	}
	public String getTempletPath() {
		return templetPath;
	}
	public void setTempletPath(String templetPath) {
		this.templetPath = templetPath.replaceAll("\\.", "/");
	}
	public String getCreatePath() {
		return createPath;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public HashMap getRelations() {
		return relations;
	}
	public void setRelations(HashMap relations) {
		this.relations = relations;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	
}