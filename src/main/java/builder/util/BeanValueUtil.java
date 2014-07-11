/**
 *
 *Copyright (c) 2007. All rights reserved.
 *@author wangwei<wangw98@gmail.com>
 *@version 2007-7-27下午07:04:43
 */
package builder.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BeanValueUtil {
	private static String getMethodName(String fieldName) {
		String result = "";
		String theFirstChar = null;
		theFirstChar = fieldName.substring(0, 1);
		theFirstChar = theFirstChar.toUpperCase();
		result = "set" + theFirstChar
				+ fieldName.substring(1, fieldName.length());
		return result;
	}

	private static Method getMethod(Method[] methods, String methodName) {
		Method method = null;
		for (int i = 0; i < methods.length; i++) {
			method = methods[i];
			if (method.getName().equals(methodName)) {
				break;
			}
		}
		return method;
	}

	public static int hasImplementNumberInterface(Class superClass) {
//		if ( superClass == null ) return 0;
		String type = superClass.getName();
		if (type.equals("java.lang.Number")) {
			return 1;
		} else if ( type.equals("java.util.AbstractMap") ) {
			return 2;
		}
		return 0;
//		if ( superClass.getName().equals("java.lang.Number") 
//				|| superClass.getName().equals("class java.util.AbstractMap") ) {
//			return true;
//		} else {
//			return false;
//		}
	}

	@SuppressWarnings("unchecked")
	public static Object convertParameterType(Method method, Object setValue) {
		Object result = null;
		Class[] parameterClasses = method.getParameterTypes();
		Class parameterClass = null;

		Constructor constructor = null;
		for (int i = 0; i < parameterClasses.length; i++) {
			parameterClass = parameterClasses[i];
			if ( hasImplementNumberInterface(parameterClass.getSuperclass()) == 1 ) {
				try {
					constructor = parameterClass.getConstructor(new Class[] { String.class });
					result = constructor.newInstance(new Object[] { setValue });
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (parameterClass.getName().equals("java.lang.String")) {
				result = setValue;
			} else if ( hasImplementNumberInterface(parameterClass.getSuperclass()) == 2 ) {
				try {
					constructor = parameterClass.getConstructor(new Class[] { Map.class });
					result = constructor.newInstance(new Object[] { setValue });
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		}
		return result;
	}

	public static void setObjectInfo(Object theObject, Map theSetValueMap) {
		Class theClass = theObject.getClass();
		Field[] theFieldArray = theClass.getDeclaredFields();
		Field field = null;
		String fieldName = null;
		String methodName = null;
		Method[] theMethods = theClass.getDeclaredMethods();
		for (int i = 0; i < theFieldArray.length; i++) {
			field = theFieldArray[i];
			fieldName = field.getName();
			if (!fieldName.equals("serialVersionUID") && !fieldName.equals("doc"))
				methodName = getMethodName(fieldName);
			else
				continue;
			Method theMethod = getMethod(theMethods, methodName);
			if (theMethod != null && theSetValueMap.containsKey(fieldName)) {
				if (theSetValueMap.get(fieldName)==null || theSetValueMap.get(fieldName).toString().equals("")) {
					continue;
				} else {
					Object[] parameterObject = new Object[] { convertParameterType(
							theMethod, theSetValueMap.get(fieldName)) };
					try {
						theMethod.invoke(theObject, parameterObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void setObjectInfo(Object theObject, String[] column, String mark) {
		int len = column.length;
		Map map = new HashMap(len);
		
		for ( int i = 0; i < len; i++ ) {
			if ( column[i] != null ) {
				String temp =  column[i];
				if ( column[i].indexOf(",") > -1 ) {
					temp =  column[i].substring(0, column[i].length() - 1);
				}
				String[] temp2 = temp.split(mark);
				if ( temp2 != null && temp2.length > 1 ) {
					if ( !temp2[1].equals("null") ) map.put(temp2[0], temp2[1]);
				}
			}
		}
		setObjectInfo(theObject, map);
	}
}
