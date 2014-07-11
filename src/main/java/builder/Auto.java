package builder;

import java.util.Date;

import builder.core.IBuildeController;

/**
 * @author mkwu
 *
 *	创建文件
 */
public class Auto {
	public static void main(String[] args) {
		//1395365445866
		
		Date date = new Date();
		System.out.println(Long.MAX_VALUE);
		IBuildeController buildeController = BuilderFactory.getinstance().getBuildeController();
		buildeController.defaultBuild();
	}
}
