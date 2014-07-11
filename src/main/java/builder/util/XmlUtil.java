package builder.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import builder.model.Maker;

/**
 * @author Mr. klaus
 *
 *	操作XML
 */
public class XmlUtil {
	private static SAXReader reader = new SAXReader();
	private static Document doc;
	private static Map<String, Object> global;
	private static ArrayList<Maker> contents;
	
	@SuppressWarnings("unused")
	public static void load(String xmlFilePath) {
		if ( xmlFilePath != null && !xmlFilePath.equals("") ) {
			File file = new File(xmlFilePath);
			if ( file.exists() ) {
				try {
					doc = reader.read(file);
					setGobals();	//设置公共参数
					setContents();	//加载XML内容
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获得global元素的子元素
	 * @return result
	 * 			HashMap<String, String>,global元素子元素MAP
	 */
	private static void setGobals() {
		Element rootElement = doc.getRootElement();
		ArrayList elements = (ArrayList)rootElement.elements("global");
		if ( elements != null && elements.size() > 0 ) {
			Element element = (Element)elements.get(elements.size() - 1);
			//获得marker元素的子元素
			global = parseElement(element, new HashMap<String, Object>());
		}
	}
	
	/**
	 * 获得marker元素集的子元素集
	 */
	private static void setContents() {
		Element rootElement = doc.getRootElement();
		ArrayList elements = (ArrayList)rootElement.elements("maker");
		if ( elements != null && elements.size() > 0 ) {
			contents = new ArrayList<Maker>(elements.size());
			Iterator iterator = elements.iterator();
			while ( iterator.hasNext() ) {
				//获得marker元素
				Element element = (Element)iterator.next();
				String enable = element.attributeValue("enable");
				if ( enable == null || enable.equals("true") ) {
					//获得marker元素的子元素
					Map<String, Object> valueMap = parseElement(element, new HashMap<String, Object>());
					
					Maker maker = new Maker();
					if ( maker.getTempletPath() == null || maker.getTempletPath().equals("") ) {
						if ( global.get("templetPath") != null )
							maker.setTempletPath(global.get("templetPath").toString());
					}
					if ( maker.getBasePath() == null || maker.getBasePath().equals("") ) {
						if ( global.get("basePath") != null )
							maker.setBasePath(global.get("basePath").toString());
					}
					BeanValueUtil.setObjectInfo(maker, valueMap);
					contents.add(maker);
				}
			}
		}
	}
	
	/**
	 * 递归解析XML
	 * @param element
	 * @param valueMap
	 * @return Map<String, Object>
	 */
	private static Map<String, Object> parseElement(Element element, Map<String, Object> valueMap) {
		if ( element.isTextOnly() ) {
			if ( element.getText() != null && !element.getText().equals("") ) {
				valueMap.put(element.getName(), element.getText());
			}
			if ( isContainAttribute(element) ){
				Map attributeMap = parseElementAttribute(element);
				valueMap.put(getAttributeName(element, attributeMap), attributeMap);
			}
		} else {
			ArrayList childElements = getElements(element);
			if ( childElements != null && childElements.size() > 0 ) {
				Iterator childIterator = childElements.iterator();
				while ( childIterator.hasNext() ) {
					Element childElement = (Element)childIterator.next();
					if ( childElement.isTextOnly() ) {
						if ( childElement.getText() != null && !childElement.getText().equals("") )
							valueMap.put(childElement.getName(), childElement.getText());
					} else {
						valueMap.put(childElement.getName(), parseElement(childElement, new HashMap<String, Object>()));
					}
					if ( isContainAttribute(childElement) ){
						Map attributeMap = parseElementAttribute(childElement);
						valueMap.put(getAttributeName(childElement, attributeMap), attributeMap);
					}
				}
			}
		}
		return valueMap;
	}
	
	
	//---------------------------------------------------------------
	//						以下为MAP操作方法
	//--------------------------------------------------------------
	
	/**
	 * 生成属性名称
	 * @return String
	 */
	private static String getAttributeName(Element element, Map attributeMap) {
		if ( attributeMap.containsKey("field") && !attributeMap.get("field").equals("") ) {
			return attributeMap.get("field") + "Attributes";
		} else if ( attributeMap.containsKey("id") && !attributeMap.get("id").equals("") ) {
			return attributeMap.get("id") + "Attributes";
		} else {
			return element.getName() + "Attributes";
		}
	}
	
	/**
	 * 解析元素属性
	 * @param element
	 * @return Map<String, String>
	 */
	private static Map<String, String> parseElementAttribute(Element element) { 
		List attributes = element.attributes();
		if ( attributes != null && attributes.size() > 0 ) {
			Map<String, String> attributeMap = new HashMap<String, String>(attributes.size());
			int len = attributes.size();
			for ( int i = 0; i < len; i++ ) {
				Attribute attribute = (Attribute)attributes.get(i);
				attributeMap.put(attribute.getName(), attribute.getValue());
			}
			return attributeMap;
		} else {
			return null;
		}
	}
	
	/**
	 * 判断元素是否有属性
	 * @param element
	 * @return boolean
	 * 			true:有	false:无
	 */
	private static boolean isContainAttribute(Element element) {
		if ( element != null && element.attributes().size() > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获得元素的子元素
	 * @param element
	 * @return ArrayList
	 */
	private static ArrayList getElements(Element element) {
		if ( element != null ) {
			return (ArrayList)element.elements();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得表关系的属性MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAttributeMapOfRelations(Maker maker) {
		try {
			return getAttributeMap(maker.getRelations());
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得表关系的非属性MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapOfRelations(Maker maker) {
		try {
			return getNotAttributeMap(maker.getRelations());
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得框架配置信息
	 * @param maker
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getMapOfFrames() {
		try {
			return getValueOfMap(global, "frames");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得框架配置属性信息
	 * @param maker
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getMapOfFramesAttribute() {
		try {
			return getValueOfMap(global, "framesAttributes");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得Spring属性MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getMapOfSpringAttribute() {
		try {
			return getValueOfMap(getMapOfFrames(), "springAttributes");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得Spring MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapOfSpring() {
		try {
			return getValueOfMap(getMapOfFrames(), "spring");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得SQL属性MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getMapOfSQLAttribute() {
		try {
			return getValueOfMap(getMapOfFrames(), "sqlAttributes");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得SQL MAP
	 * @param maker
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapOfSQL() {
		try {
			return getValueOfMap(getMapOfFrames(), "sql");
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得MAP值
	 * @param map
	 * @param key
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getValueOfMap(Map map, String key) {
		return (Map<String, Object>) map.get(key);
	}
	
	/**
	 * 获得传入MAP中的非属性Map
	 * @param map
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getNotAttributeMap(Map<String, Object> map) {
		if ( !map.isEmpty() ) {
			Iterator it = map.entrySet().iterator();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			while ( it.hasNext() ) {   
			  Map.Entry entry = (Map.Entry) it.next();
			  String key = entry.getKey().toString();
			  if ( key != null && key.indexOf("Attributes") == -1 ) {
				  Map value = (Map)entry.getValue();
				  if ( !value.isEmpty() ) resultMap.put(key, value);
			  }
			}
			return resultMap;
		} else {
			return null;
		}
	}
	
	/**
	 * 获得传入MAP中的属性MAP
	 * @param map
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getAttributeMap(Map<String, Object> map) {
		if ( !map.isEmpty() ) {
			Iterator it = map.entrySet().iterator();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			while ( it.hasNext() ) {   
			  Map.Entry entry = (Map.Entry) it.next();
			  String key = entry.getKey().toString();
			  if ( key != null && key.indexOf("Attributes") > 0 ) {
				  Map value = (Map)entry.getValue();
				  if ( !value.isEmpty() ) resultMap.put(key, value);
			  }
			}
			return resultMap;
		} else {
			return null;
		}
	}

	/**
	 * 获得框架配置文件路径
	 * @return String
	 * 			框架配置文件存放路径(返回的路径包含文件名)
	 */
	public static String getFramesConfigFilePath() {
		try {
			Map<String, Object> framesMap = getMapOfFrames();
			String path = CommonUtil.getPath(framesMap.get("configFilePath").toString());
			if ( CommonUtil.isExists(path) )
				return path.toString();
			else
				return DefaultPathUtil.getFramesConfigFileDefaultPath(getMapOfFramesAttribute(), path);
		} catch ( Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得Spring配置文件路径
	 * @return String
	 * 			Spring配置文件存放路径(返回的路径包含文件名)
	 */
	public static String getSpringConfigFilePath(String pathInfo) {
		try {
			Map groupMap = getValueOfMap(getMapOfSpring(), "group");
			String path = CommonUtil.getPath(groupMap.get(pathInfo).toString());
			if ( CommonUtil.isExists(path) )
				return path.toString();
			else
				return DefaultPathUtil.getSpringConfigFileDefaultPath(path);
		} catch ( Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获得SQL配置文件路径
	 * @return String
	 * 			SQL配置文件存放路径(返回的路径包含文件名)
	 */
	public static String getSqlConfigFilePath(Maker maker) {
		try {
			Map<String, Object> sqlMap = getMapOfSQL();
			String path = CommonUtil.getPath(sqlMap.get("configFilePath").toString());
			if ( CommonUtil.isExists(path) )
				return path.toString();
			else
				return DefaultPathUtil.getSqlConfigFileDefaultPath(path);
		} catch ( Exception e) {
			return null;
		}
	}
	
//	/**
//	 * 获得JSP文件夹路径
//	 * @return String
//	 * 			JSP文件夹路径
//	 */
//	public static String getJspFolderPath(Maker maker) {
//		try {
//			Map jspMap = maker.getJsp();
//			Object path = jspMap.get("configFilePath");
//			if ( CommonUtil.isExists(path.toString()) )
//				return path.toString();
//			else
//				return DefaultPathUtil.getJspFolderDefaultPath(path.toString());
//		} catch ( Exception e) {
//			return null;
//		}
//	}
	
	public static Document getDoc() {
		return doc;
	}

	public static void setDoc(Document doc) {
		XmlUtil.doc = doc;
	}

	public static ArrayList<Maker> getContents() {
		return contents;
	}

	public static void setContents(ArrayList<Maker> contents) {
		XmlUtil.contents = contents;
	}

	public static Map<String, Object> getGlobal() {
		return global;
	}

	public static void setGlobal(Map<String, Object> global) {
		XmlUtil.global = global;
	}
}