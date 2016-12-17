package gametank.paint.tool;

import java.io.InputStream;

import javazoom.jl.player.Player;

/**
 * 
 *  声音播放类,使用第三方jar包实现
 *  作为线程实现,可以使其单独为一个线程,不会影响其他的
 *  因此可以作为背景音乐来播放
 *  music/ 为根目录
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
	//播放声音
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
