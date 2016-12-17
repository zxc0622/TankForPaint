package gametank.paint.tool;

import java.io.InputStream;

import javazoom.jl.player.Player;

/**
 * 
 *  ����������,ʹ�õ�����jar��ʵ��
 *  ��Ϊ�߳�ʵ��,����ʹ�䵥��Ϊһ���߳�,����Ӱ��������
 *  ��˿�����Ϊ��������������
 *  music/ Ϊ��Ŀ¼
 */
public class Mysound extends Thread{
	private String fileName="music/";
	private Player player;
	private InputStream in;
	public InputStream getIn() {
		return in;
	}
	public Player getPlayer() {
		return player;
	}
	public Mysound(String fileName) {
		this.fileName +=fileName;
	}
	public String getFileName() {
		return fileName;
	}
	//��������
	public void play(){
		in=getClass().getClassLoader()
				.getResourceAsStream(fileName);
		try {
			player=new Player(in);
			player.play();
			if(in!=null){
				in.close();
				player=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
