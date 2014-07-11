package builder.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class VelocityUtil {
    private VelocityContext context = null;   
    private Template template = null;   
    private String templateString = null ;
    private VelocityEngine velocityEngine = null;
    
    public void init(String propertiesPath) throws Exception {   
        if (propertiesPath != null && propertiesPath.trim().length() > 0) {   
            Velocity.init(propertiesPath);   
        } else {   
            Velocity.init();   
        }
        context = new VelocityContext();   
    }   
  
    public void init(Properties properties) throws Exception {   	  
        Velocity.init(properties);   
        context = new VelocityContext();   
    }
    
    public void init() throws Exception {
    	Properties p = new Properties();   
		p.setProperty("resource.loader", "class");   
		p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		//设置velocity的编码
        p.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
        p.setProperty(Velocity.INPUT_ENCODING, "GBK");
        p.setProperty(Velocity.OUTPUT_ENCODING, "GBK"); 
        
		Velocity.init(p);
		context = new VelocityContext();   
    }
    
    /**
     * 设置模块路径
     * @param path
     * 			模块路径
     * @throws Exception
     */
    public void initVelocityEngine(String path) throws Exception {
    	velocityEngine = new VelocityEngine();
	 	Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
		//设置velocity的编码
		properties.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
	 	properties.setProperty(Velocity.INPUT_ENCODING, "GBK");
	   	properties.setProperty(Velocity.OUTPUT_ENCODING, "GBK"); 
	   	velocityEngine.init(properties);
    }
  
    /**    
     * @param key  
     * @param value  
     */  
    public void put(String key, Object value) {   
        context.put(key, value);   
    }   
  
    /**  
     * 设置模版    
     * @param templateFile  模版文件            
     * @throws Exception  
     */  
    public void setTemplate(String templateFile) throws Exception {   
        try {
            template = Velocity.getTemplate(templateFile);
        } catch (ResourceNotFoundException rnfe) {   
            rnfe.printStackTrace();
            throw new Exception(" 错误： 找不到模板 " + templateFile);   
        } catch (ParseErrorException pee) {   
            throw new Exception(" 解析模板错误 " + templateFile + ":" + pee);   
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
    
    /**  
     * 设置模版    
     * @param templateFile  模版文件            
     * @throws Exception  
     */  
    public void setTemplateByEngine(String templateFile) throws Exception {   
        try {
            template = velocityEngine.getTemplate(templateFile);
        } catch (ResourceNotFoundException rnfe) {   
            rnfe.printStackTrace();
            throw new Exception(" 错误： 找不到模板 " + templateFile);   
        } catch (ParseErrorException pee) {   
            throw new Exception(" 解析模板错误 " + templateFile + ":" + pee);   
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
  
    /**  
     * 设置模版    
     * @param templateFile 模版文件  
     * @throws Exception  
     */  
    public void setTemplate(String templateFile, String characterSet)   
            throws Exception {   
        try {   
            template = Velocity.getTemplate(templateFile, characterSet);   
        } catch (ResourceNotFoundException rnfe) {   
            rnfe.printStackTrace();   
            throw new Exception(" 错误： 找不到模板 " + templateFile);   
        } catch (ParseErrorException pee) {   
            throw new Exception(" 解悉模板错误 " + templateFile + ":" + pee);   
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
  
    }
    
    public void setTemplate(Template template) throws Exception {
    	try {   
            this.template = template;
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
    
    /**
     * 将输入流转换成字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String getStringFromStream(InputStream is) throws IOException {
        if (null == is)
            return null;
        try {
            InputStreamReader reader = new InputStreamReader(is);
            char[] buffer = new char[1024];
            StringWriter writer = new StringWriter();
            int bytes_read;
            while ((bytes_read = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytes_read);
            }
            return (writer.toString());
        } finally {
            if (null != is)
                is.close();
        }
    }
    
    public void setTemplateString(String templateFile, String characterSet){     
    	   
    	try {
    		InputStream is = this.getClass().getClassLoader().getResourceAsStream(templateFile);
			templateString = getStringFromStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
  
    /**  
     * 转换为文本  
     */  
    public String toText() throws Exception {   
        StringWriter sw = new StringWriter();   
        try {   
            template.merge(context, sw);   
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
        return sw.toString();   
    }
    /**  
     * 转换为文本  
     */  
    public String toTextWithTemplateString() throws Exception {   
        StringWriter sw = new StringWriter();   
        try {   
        	Velocity.evaluate(context,sw,"mystring",templateString);  
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
        return sw.toString();   
    }
  
    /**  
     *   
     * @param fileName  
     */  
    public void toFile(String path,String fileName) throws Exception {   
        try {
        	//替换模板内容
        	StringWriter sw = new StringWriter();   
            template.merge(context, sw);
        	//创建目录路径
        	File dir=new File(path);
        	if(!dir.exists()){
        		dir.mkdirs();
        	}
			//创建文件
			File file = new File(dir.getPath(),fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	        out.println(sw.toString());   
	        out.close(); 
            /*
            PrintWriter filewriter = new PrintWriter(new FileOutputStream(fileName), true);   
            filewriter.println(sw.toString());   
            filewriter.close();
            */
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
    
    /**  
     *   
     * @param fileName  
     */  
    public void toFile(String path) throws Exception {   
        try {
        	//替换模板内容
        	StringWriter sw = new StringWriter();   
            template.merge(context, sw);
        	//创建目录路径
        	File dir = new File(path.substring(0, path.lastIndexOf("/")));
        	if(!dir.exists()) dir.mkdirs();
			//创建文件
			File file = new File(path);
			if(!file.exists()) file.createNewFile();
			
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	        out.println(sw.toString());   
	        out.close(); 
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
	    
    /**  
     *   
     * @param fileName  
     */  
    public void toFileWithTemplateString(String path,String fileName) throws Exception {   
        try {
        	//替换模板内容
        	StringWriter sw = new StringWriter();   
        	Velocity.evaluate(context,sw,"mystring",templateString);
        	
        	//创建目录路径
        	File dir=new File(path);
        	if(!dir.exists()){
        		dir.mkdirs();
        	}
			//创建文件
			File file = new File(dir.getPath(),fileName+".java");
			if(!file.exists()){
				file.createNewFile();
			}
			
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	        out.println(sw.toString());   
	        out.close(); 
            /*
            PrintWriter filewriter = new PrintWriter(new FileOutputStream(fileName), true);   
            filewriter.println(sw.toString());   
            filewriter.close();
            */
        } catch (Exception e) {   
            throw new Exception(e.toString());   
        }   
    }
	    
}