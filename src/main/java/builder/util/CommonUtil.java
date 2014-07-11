package builder.util;

import java.io.File;
import java.util.Map;

/**
 * @author Mr. klaus
 *
 * 常用方法
 */
public class CommonUtil {
	/**
	 * 首字母大写
	 * @param str
	 * @return String
	 */
	public static String firstUpper(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1,str.length());
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return String
	 */
	public static String firstLower(String str) {
		return str.substring(0,1).toLowerCase() + str.substring(1,str.length());
	}
	
	/**
	 * 判断文件或文件夹是否存在
	 * @param path
	 * 			路径
	 * @return boolean
	 * 			true 存在, false 不存在
	 */
	public static boolean isExists(String filePath) {
		if ( filePath != null && !filePath.equals("") ) {
			File file = new File(filePath);
			if ( file.exists() ) return true;
		}
		return false;
	}
	
	/**
	 * 判断MAP是否为空
	 * @param element
	 * @return boolean
	 * 			true:不为空	false:空
	 */
	public static boolean isEmpty(Map map) {
		if ( map == null ) return false;
		return !map.isEmpty();
	}
	
	/**
	 * 根据传入的包名(package)返回路径
	 * @param str
	 * @return String
	 */
	public static String getPath(String str) {
		String path = str.replaceAll("\\.", "/");
		return new StringBuilder(path.length())
				.append(path.substring(0, path.lastIndexOf("/"))).append(".")
				.append(path.substring(path.lastIndexOf("/") + 1)).toString();
	}
}