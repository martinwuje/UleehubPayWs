package builder.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import builder.core.IBuilder;
import builder.model.Maker;
import builder.model.TableColumns;
import builder.util.CommonUtil;
import builder.util.VelocityUtil;
import builder.util.XmlUtil;

/**
 * @author Mr. klaus
 *
 *	模板接口实现
 */
public class Builder implements IBuilder {
	//该List主要用于Bean等的创建
	private List<TableColumns> list;
	//该List主要用于Form、QueryBean等的创建
	private ArrayList<TableColumns> copyList;
	private VelocityUtil agt;
	private Maker maker;
	private StringBuilder imports;
	private StringBuilder tables;
	private StringBuilder inquirys;
	
	public Builder(Maker maker, List<TableColumns> list, VelocityUtil agt){
		this.maker = maker;
		this.list = list;
		this.agt = agt;
		imports = new StringBuilder();
		copyList = new ArrayList<TableColumns>(list.size());
		tables = new StringBuilder();
		inquirys = new StringBuilder();
		setColName();
		setImortsByColumns();
	}
	
	public void buildeJavaBean(){
		try {
			agt.setTemplate(maker.getTempletPath()+"/Entity.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("tableName", maker.getTableName());
			agt.put("date", new Date().toLocaleString());
			agt.put("author", maker.getAuthor());
			agt.put("imports", imports.toString());
			agt.put("list", list);
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/entity/";
	        String fileName = maker.getEntityName()+".java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildeQueryJavaBean(){
		try {
			agt.setTemplate(maker.getTempletPath()+"/QueryModel.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			agt.put("list", copyList);
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/model/";
	        String fileName = "Query" + maker.getEntityName() + ".java";
	        agt.toFile(path, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildeIDAO() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/IDAO.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/repository/";
	        String fileName = maker.getEntityName() + "Repository.java";
	        agt.toFile(path, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildeDAOImpl() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/DAOImpl.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/dao/impl/";
	        String fileName = maker.getEntityName() + "DAOiBatis.java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildeIBatis() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/Ibatis.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			agt.put("tableName", maker.getTableName());
			agt.put("tableAliasName", maker.getTableAliasName());
			agt.put("entityDesc", maker.getEntityDesc());
			agt.put("tables", tables.toString());
			agt.put("inquirys", inquirys.toString());
			agt.put("list", list);
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/model/mapper/";
	        String fileName = maker.getEntityName()+"Mapper.xml";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildeIService() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/IService.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/service/";
	        String fileName = maker.getEntityName() + "Service.java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildeServiceImpl() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/ServiceImpl.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/service/impl/";
	        String fileName = maker.getEntityName() + "ServiceImpl.java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildeAction() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/Action.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			agt.put("list", list);
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/web/";
	        String fileName = maker.getEntityName() + "Action.java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void buildeForm() {
		try {
			agt.setTemplate(maker.getTempletPath()+"/Form.vm");
			agt.put("packageName", maker.getPackageName());
			agt.put("entityName", maker.getEntityName());
			agt.put("author", maker.getAuthor());
			agt.put("list", copyList);
			
			String path = System.getProperty("user.dir")+"/"+maker.getCreatePath()+"/web/actionform/";
	        String fileName = maker.getEntityName() + "Form.java";
	        agt.toFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void configFrames() {
		//配置STRUTS
		Map framesAttributeMap = XmlUtil.getMapOfFramesAttribute();
		Object type = framesAttributeMap.get("type");
		if ( type != null && type.equals("struts") ) {
			configStruts();
		}
		//配置Ibatis
		Map sqlAttributeMap = XmlUtil.getMapOfSQLAttribute();
		Object sqlEnable = sqlAttributeMap.get("enable");
		if ( sqlEnable == null || sqlEnable.toString().equals("true") ) {
			configIBatis();
		}
		//配置Spring
		Map springAttributeMap = XmlUtil.getMapOfSpringAttribute();
		//判断是否启用Spring配置程序
		Object springEnable = springAttributeMap.get("enable");
		if ( springEnable == null || springEnable.toString().equals("true") ) {
			configSpring();
		}
	}
	
	/**
	 * 配置Spring
	 */
	private void configSpring() {
		Map springMap = XmlUtil.getMapOfSpring();
		Map springAttributeMap = XmlUtil.getMapOfSpringAttribute();
		
		//判断XML文件中是否配置了Spring标签
		if ( CommonUtil.isEmpty(springAttributeMap) && CommonUtil.isEmpty(springMap) ) {
			//判断是否分割配置Spring配置文件
			Object group = springAttributeMap.get("group");
			if ( group != null && group.toString().equals("true") ) {
				Map groupMap = (Map) springMap.get("group");
				if ( CommonUtil.isEmpty(groupMap) )
					configSpring(new String[]{"dao","service","action"});
			} else configSpring(new String[]{"configFilePath"});
		}
	}
	
	/**
	 * 配置Struts
	 */
	private void configStruts() {
		String configFilePath = XmlUtil.getFramesConfigFilePath();
		if ( configFilePath != null && !configFilePath.equals("") ) {
			String packageName = maker.getPackageName();
			String entityName = maker.getEntityName();
			try {
				agt.initVelocityEngine(configFilePath.substring(0, configFilePath.lastIndexOf("/")));
				agt.setTemplateByEngine(configFilePath.substring(configFilePath.lastIndexOf("/") + 1));
				//框架配置
				StringBuffer forms = new StringBuffer();
				forms.append("<form-bean name=\"").append(CommonUtil.firstLower(entityName)).append("Form\" ")
						.append("type=\"").append(packageName).append(".web.actionform.").append(entityName)
						.append("Form").append("\"/>");
				forms.append("\n\t\t-->\n")
						.append("\t\t<!--\n")
						.append("\t\t${config-forms}");
				
				StringBuffer actions = new StringBuffer();
				actions.append("<action path=\"").append(CommonUtil.firstLower(entityName)).append("\" ")
						.append("name=\"").append(CommonUtil.firstLower(entityName)).append("Form\"")
						.append("\n\t\t\ttype=\"").append("org.springframework.web.struts.DelegatingActionProxy").append("\"")
						.append("\n\t\t\tscope=\"request\" ").append("validate=\"true\" ").append("parameter=\"reqCode\">")
						.append("\n\t\t\t<forward name=\"browse\" path=\"").append("/browse_").append(CommonUtil.firstLower(entityName)).append(".jsp\"/>")
						.append("\n\t\t\t<forward name=\"manage\" path=\"").append("/manage_").append(CommonUtil.firstLower(entityName)).append(".jsp\"/>")
						.append("\n\t\t\t<forward name=\"showAdd\" path=\"").append("/showAdd_").append(CommonUtil.firstLower(entityName)).append(".jsp\"/>")
						.append("\n\t\t\t<forward name=\"showEdit\" path=\"").append("/showEdit_").append(CommonUtil.firstLower(entityName)).append(".jsp\"/>")
						.append("\n\t\t\t<forward name=\"detail\" path=\"").append("/detail_").append(CommonUtil.firstLower(entityName)).append(".jsp\"/>")
						.append("\n\t\t</action>");
				actions.append("\n\t\t-->\n")
				.append("\t\t<!--\n")
				.append("\t\t${config-actions}");
				
				agt.put("config-forms", forms.toString());
				agt.put("config-actions", actions.toString());
		        agt.toFile(configFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * 配置IBatis
	 */
	private void configIBatis() {
		String configFilePath = XmlUtil.getSqlConfigFilePath(maker);
		if ( configFilePath != null && !configFilePath.equals("") ) {
			try {
				agt.initVelocityEngine(configFilePath.substring(0, configFilePath.lastIndexOf("/")));
				agt.setTemplateByEngine(configFilePath.substring(configFilePath.lastIndexOf("/") + 1));
				
				StringBuffer sb = new StringBuffer();
				sb.append("<sqlMap resource=\"" + maker.getCreatePath() + "/dao/impl/sql/" + 
						CommonUtil.firstUpper(maker.getEntityName()) + "SQL.xml\" />\n");
				sb.append("\t-->\n");
				sb.append("\t<!--\n");
				sb.append("\t${object_conf}");
				
				agt.put("object_conf", sb.toString());
		        agt.toFile(configFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 配置Spring
	 */
	private void configSpring(String[] pathInfo) {
		String packageName = maker.getPackageName();
		String entityName = maker.getEntityName();
		int len = pathInfo.length;
		
		for ( int i = 0; i < len; i++ ) {
			String configFilePath = XmlUtil.getSpringConfigFilePath(pathInfo[i]);
			if ( configFilePath == null || configFilePath.equals("") ) continue;
			try {
				agt.initVelocityEngine(configFilePath.substring(0, configFilePath.lastIndexOf("/")));
				agt.setTemplateByEngine(configFilePath.substring(configFilePath.lastIndexOf("/") + 1));
				
				StringBuffer sb = new StringBuffer();
				if ( pathInfo[i].equals("dao") ) {
					sb.append("<bean id=\"").append(CommonUtil.firstLower(entityName)).append("DAO").append("\" class=\"")
							.append(packageName).append(".dao.impl.").append(entityName).append("DAOImpl").append("\">")
							.append("\n\t\t<property name=\"dataSource\" ref=\"dataSource\"/>")
							.append("\n\t\t<property name=\"sqlMapClient\" ref=\"sqlMapClient\"/>")
							.append("\n\t</bean>");
				} else if ( pathInfo[i].equals("service") ) {
					sb.append("<bean id=\"").append(CommonUtil.firstLower(entityName)).append("Service")
							.append("\" parent=\"txProxyTemplate\">\n\t\t<property name=\"target\">\n\t\t\t<bean class=\"")
							.append(packageName).append(".service.impl.").append(entityName).append("ServiceImpl").append("\">")
							.append("\n\t\t\t\t<property name=\"").append("i").append(entityName).append("DAO").append("\" ")
							.append("ref=\"").append(CommonUtil.firstLower(entityName)).append("DAO").append("\"/>")
							.append("\n\t\t\t</bean>\n\t\t</property>\n\t</bean>");
				} else if ( pathInfo[i].equals("action") ) {
					sb.append("<bean name=\"/").append(CommonUtil.firstLower(entityName)).append("\" \n\t\tclass=\"")
							.append(packageName).append(".web.action.").append(entityName).append("Action\" \n\t\tsingleton=\"false\">")
							.append("\n\t\t<property name=\"").append("i").append(entityName).append("Service")
							.append("\" ref=\"").append(CommonUtil.firstLower(entityName)).append("Service").append("\"/>")
							.append("\n\t</bean>");
				} else {
					
				}
				sb.append("\n\t-->");
				sb.append("\n\t<!--\n");
				sb.append("\t${config-spring}");
				
				agt.put("config-spring", sb.toString());
		        agt.toFile(configFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置列信息
	 */
	private void setColName() {
		int len = list.size();
		Map<String, Object> attributeMaps = XmlUtil.getAttributeMapOfRelations(maker);
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < len; i++ ) {
			TableColumns tCol = list.get(i);
			StringBuilder attributeKey = new StringBuilder().append(tCol.getDbColumnName()).append("Attributes");
			if ( attributeMaps != null && attributeMaps.containsKey(attributeKey.toString()) ) {
				Map attributeMap = (Map)attributeMaps.get(attributeKey.toString());
				//设置创建FormBean时所要使用的List
				TableColumns copyCol = new TableColumns(tCol.toString());
				copyCol.setJavaType("Integer");
				copyList.add(copyCol);
				//设置创建Bean时所要使用的List
				Object fetch = attributeMap.get("fetch");
				Object javaTypeStr = attributeMap.get("javaType");
				
				if ( fetch != null && fetch.toString().equals("true") 
						&& javaTypeStr != null && !javaTypeStr.toString().equals("") ) {
					String[] javaType = javaTypeStr.toString().split("\\.");
					tCol.setJavaType(javaType[javaType.length - 1]);
					tCol.setMethodSuffix(tCol.getJavaType());
					//设置抓取记录
					tCol.setFetch(true);
					//设置变量名称
//					int len2 = javaType.length - 1;
//					String methodSuffix = javaType[len2].substring(0,1).toLowerCase() + 
//							javaType[len2].substring(1,javaType[len2].length());
					String[] temp = CommonUtil.firstLower(
							tCol.getColumnName().substring(0, tCol.getColumnName().lastIndexOf("_"))).split("_");
					sb.delete(0, sb.length());
					for ( int k = 0; k < temp.length; k++ )
						sb.append(k == 0 ? temp[k] : CommonUtil.firstUpper(temp[k]));
					String colName = sb.toString();
					tCol.setColumnName(colName);
					copyCol.setColumnName(colName + "Id");
					//设置import
					imports.append("import ").append(javaTypeStr).append(";\n");
				} else {
					tCol.setJavaType("Integer");
					String[] temp = tCol.getColumnName().split("_");
					sb.delete(0, sb.length());
					for ( int k = 0; k < temp.length; k++ )
						sb.append(k == 0 ? temp[k] : CommonUtil.firstUpper(temp[k]));
					String colName = sb.toString();
//					String colName = tCol.getColumnName().substring(tCol.getColumnName().indexOf("_") + 1);
					tCol.setColumnName(colName);
					copyCol.setColumnName(colName);
				}
				
				Object foreignKeyTable = attributeMap.get("table");
				Object foreignKeyAlias = attributeMap.get("alias");
				if ( foreignKeyTable != null && foreignKeyAlias != null ) {
					//设置多表查询时的表名以及表连接
					tables.append(foreignKeyTable).append(" ").append(foreignKeyAlias).append(",");
					inquirys.append(maker.getTableAliasName()).append(".").append(tCol.getDbColumnName())
							.append("=").append(foreignKeyAlias).append(".").append(tCol.getDbColumnName()).append(" and ");
				}
			} else {
				String[] temp = tCol.getColumnName().split("_");
				sb.delete(0, sb.length());
				for ( int k = 0; k < temp.length; k++ )
					sb.append(k == 0 ? temp[k] : CommonUtil.firstUpper(temp[k]));
				tCol.setColumnName(sb.toString());
				//设置创建FormBean时所要使用的List
				TableColumns copyCol = new TableColumns(tCol.toString());
				copyList.add(copyCol);
			}
		}
		tables.append(maker.getTableName()).append(" ").append(maker.getTableAliasName());
		inquirys.append("1=1");
	}
	public void setImortsByColumns(){
		int len = list.size();
		//判断Date
		for ( int i = 0; i < len; i++ ) {
			TableColumns tCol = list.get(i);
			if(tCol.getJavaType().equals("Date")){
				imports.append("import java.util.Date;\n");
				break;
			}
		}
	}
}