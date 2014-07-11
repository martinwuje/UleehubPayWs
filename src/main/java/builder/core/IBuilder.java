/**
 * 
 */
package builder.core;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף��
 * @version 1.0
 */
public interface IBuilder {

	public void buildeJavaBean();
	
	public void buildeQueryJavaBean();
	
	public void buildeIDAO();
	
	//public void buildeDAOImpl();
	
	public void buildeIBatis();
	
	public void buildeIService();
	
	public void buildeServiceImpl();
	
	public void buildeAction();
	
	//public void buildeForm();
	
	public void configFrames();
}
