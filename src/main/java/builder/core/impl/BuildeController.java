package builder.core.impl;

import java.util.ArrayList;
import java.util.List;

import builder.BuilderFactory;
import builder.core.IBuildeController;
import builder.core.IBuilder;
import builder.model.Maker;
import builder.model.TableColumns;
import builder.util.DBUtil;
import builder.util.VelocityUtil;
import builder.util.XmlUtil;

/**
 * @author Mr. klaus
 *
 * 模块生成控制器接口实现
 */
public class BuildeController implements IBuildeController {

	public void defaultBuild() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("---------------------------------------------------------------------\n\t\t")
    		.append("~!~ building,please wait!...")
    		.append("\n---------------------------------------------------------------------");
			System.out.println(sb.toString());
			String xmlFilePath = System.getProperty("user.dir") + "/src/main/java/builder/builder-config.xml";
			System.out.println("xmlFilePath:"+xmlFilePath);
			XmlUtil.load(xmlFilePath);
			ArrayList<Maker> makers = XmlUtil.getContents();
            int len = makers.size();
            for ( int i = 0; i < len; i++ ) {
            	Maker maker = makers.get(i);
            	buildeDefaultFiles(maker);
            }
            sb = new StringBuilder();
            sb.append("---------------------------------------------------------------------\n\t\t")
            		.append("~!~ build success!")
            		.append("\n---------------------------------------------------------------------");
            System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建默认文件
	 */
	private  void buildeDefaultFiles(Maker maker) {
	    //获取数据库信息
		List<TableColumns> list = DBUtil.getDbTableInfo(maker.getTableName(), false);
		VelocityUtil agt = new VelocityUtil();  
        try {
			agt.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IBuilder builder = BuilderFactory.getinstance().getBuilder(maker, list, agt);
		//创建JavaBean类
		builder.buildeJavaBean();
		//创建QueryJavaBean类
		//builder.buildeQueryJavaBean();
		
		//创建DAO接口类
		builder.buildeIDAO();
		//创建DAO接口实现类
		//builder.buildeDAOImpl();
		
		//创建Service接口类
		builder.buildeIService();
		//创建Service接口实现类
		//builder.buildeServiceImpl();
		//创建IBatis XML文件
		//builder.buildeIBatis();
		//创建Struts Action 文件
		//builder.buildeAction();
		//创建Struts Form 文件
		//builder.buildeForm();
		//根据XML中的frames标签内容配置框架中的配置文件
//		builder.configFrames();
	}
}