package gametank.paint.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CurrentLevel {
	/**
	 * 获取一些配置信息所用
	 */
	public static Map<String,String> map=new HashMap<String,String>();
	static{
		InputStream in=CurrentLevel.class.getClassLoader().getResourceAsStream("level.properties");
		Properties properties=new Properties();
		try {
			properties.load(in);
			for(Map.Entry<Object,Object> entry:properties.entrySet()){
				String key=(String) entry.getKey();
				String value=(String) entry.getValue();
				map.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用于保存当前关卡各种类型的坦克
	 */
	public static Map<TankType,Integer> enemys=new HashMap<TankType,Integer>();
}
