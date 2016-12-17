package gametank.paint.tool;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;

@SuppressWarnings("all")
public class Scene {
	
	private static ScriptEngineManager manager=new ScriptEngineManager();
	private static ScriptEngine engine=manager.getEngineByName("javascript");
	private static Invocable invocable=(Invocable) engine;
	private static Reader reader;
	
	static{
		URL url=Scene.class.getClassLoader().getResource("scene.js");
		try {
			reader=new FileReader(url.getPath());
			engine.eval(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				System.err.println("come");
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
//			//¼ÓÔØjsÎÄ¼þ
			engine.eval(reader);
			Map<String,Object> map=getMaps(1);
			Map<String,Object> map2=(Map<String, Object>) map.get("metas");
			System.err.println(map2);
//			Map<String,Object> map=(Map<String, Object>) invocable.invokeFunction("test",new Object[]{});
//			for(Map.Entry<String,Object> entry:map.entrySet()){
//				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static <T> List<T> getWall(Class<T> t,String wall){
		
		List<T> wList=new ArrayList<T>();
		try {
			Map<String,Object> map=getMaps(Constant.level);
			Map<String,Object> walls=(Map<String, Object>) map.get(wall);
			if(walls!=null){
				for(Map.Entry<String,Object> entry:walls.entrySet()){
					T entity=(T) t.getConstructor(int.class,int.class).newInstance(
							entry.getValue(),100);
					wList.add(entity);
				}
				return wList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,Object> getMaps(int i) throws Exception{
		return (Map<String, Object>) invocable.invokeFunction("como"+i,new Object[]{});
	}
}
