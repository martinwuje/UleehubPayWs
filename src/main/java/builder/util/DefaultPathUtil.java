package builder.util;

import java.util.Map;
import java.util.Properties;

/**
 * @author Mr. klaus
 *
 * 默认路径
 */
public class DefaultPathUtil {
	private static Properties props;
	
	static {
		props = PropertiesUtil.getConfigProperties();
	}
	
	/**
	 * 获得框架配置文件默认路径
	 * @param framesAttributeMap
	 * @param fileName
	 * @return String
	 * 			返回的路径包含文件名
	 */
	public static String getFramesConfigFileDefaultPath(Map<String, Object> framesAttributeMap, String pathInfo) {
		Object type = framesAttributeMap.get("type");
		Object version = framesAttributeMap.get("version");
		
		if ( type != null && !type.toString().equals("") 
				&& version != null && !version.toString().equals("") ) {
			StringBuilder sb = new StringBuilder();
			if ( type.toString().equals("struts") ) {
				float v = Float.parseFloat(version.toString());
				if ( v >= 2.0 ) {
					sb.append(System.getProperty("user.dir")).append("/src/").append(pathInfo);
				} else {
					sb.append(getWebRootPath()).append(pathInfo);
				}
			} else if ( type.toString().equals("webwork") ) {
				
			}
			//判断文件是否存在
			if ( CommonUtil.isExists(sb.toString()) ) return sb.toString();
		}
		return null;
	}
	
	/**
	 * 获得SQL配置文件默认路径
	 * @return String
	 * 			返回的路径包含文件名
	 */
	public static String getSqlConfigFileDefaultPath(String pathInfo) {
		String path = PropertiesUtil.getProperty(props, "sql.config.file.path");
		
		if ( path != null && !path.equals("") ) {
			path = path.replaceAll("\\.", "/");
			StringBuilder sb = new StringBuilder(path.length() + pathInfo.length() + 1);
			sb.append(System.getProperty("user.dir"))
				.append("/src/").append(path).append("/").append(pathInfo);
			if ( CommonUtil.isExists(sb.toString()) ) {
				return sb.toString();
			} else {
				clear(sb);
				sb.append(path).append("/").append(pathInfo);
				if ( CommonUtil.isExists(sb.toString()) ) return sb.toString();
			}
		}
		return null;
	}
	
	/**
	 * 获得Spring配置文件默认路径
	 * @return String
	 * 			返回的路径包含文件名
	 */
	public static String getSpringConfigFileDefaultPath(String pathInfo) {
		if ( pathInfo != null && !pathInfo.equals("") ) {
			StringBuilder sb = new StringBuilder();
			sb.append(System.getProperty("user.dir")).append("/src/").append(pathInfo);
			if ( CommonUtil.isExists(sb.toString()) ) {
				return sb.toString();
			} else {
				clear(sb);
				sb.append(getWebRootPath()).append(pathInfo);
				if ( CommonUtil.isExists(sb.toString()) ) return sb.toString();
			}
		}
		return null;
	}
	
	
//	/**
//	 * 获得JSP文件夹路径
//	 * @return String
//	 */
//	public static String getJspFolderDefaultPath(String folderName) {
//		if ( folderName == null || folderName.equals("") ) folderName = "sql-map-config.xml";
//		String path = PropertiesUtil.getProperty(props, "sql.config.file.path");
//		
//		if ( path != null && !path.equals("") ) {
//			StringBuilder sb = new StringBuilder(path.length());
//			path = path.replaceAll("\\.", "/");
//			sb.append(System.getProperty("user.dir"))
//				.append("/src/").append(path).append("/").append(fileName);
//			if ( CommonUtil.isExists(sb.toString()) ) {
//				return sb.toString();
//			} else {
//				clear(sb);
//				sb.append(path).append("/").append(fileName);
//				if ( CommonUtil.isExists(sb.toString()) ) 
//					return sb.toString();
//				else
//					return null;
//			}
//		}
//		return null;
//	}
	
	
	
	
	
	/**
	 * 清除StringBuilder中的数据
	 * @param sb
	 */
	private static void clear(StringBuilder sb) {
		sb.delete(0, sb.length());
	}
	
	/**
	 * 获得WebRoot路径
	 * @return String
	 */
	private static String getWebRootPath() {
		StringBuilder sb = new StringBuilder();
		String[] temp = DefaultPathUtil.class.getResource("/").getPath().split("/");
		int len = temp.length - 1;
		for ( int i = 1; i < len; i++ ) {
			sb.append(temp[i]).append("/");
		}
		return sb.toString();
	}
}