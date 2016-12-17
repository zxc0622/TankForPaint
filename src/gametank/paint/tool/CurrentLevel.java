package gametank.paint.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CurrentLevel {
	/**
	 * ��ȡһЩ������Ϣ����
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
	 * ���ڱ��浱ǰ�ؿ��������͵�̹��
	 */
	public static Map<TankType,Integer> enemys=new HashMap<TankType,Integer>();
}
