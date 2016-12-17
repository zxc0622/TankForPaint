package gametank.paint.test;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import javazoom.jl.player.Player;

public class TestLevel {

	class sound extends Applet{

		private static final long serialVersionUID = 1L;
		
		private AudioClip audioClip;
		
		public AudioClip getAudioClip() {
			return audioClip;
		}
		
		public sound() {
			URL url=getClass().getResource("tank.wav");
			audioClip=newAudioClip(url);
			System.err.println(audioClip.getClass());
		}
		
		public void start(){
			audioClip.play();
		}
	}
	
	class MySound{
		private Player player;
		
		public MySound() {
			
			InputStream input=getClass().getClassLoader()
					.getResourceAsStream("music/tank.mp3");
			try {
				player=new Player(input);
				player.play();
				if(input!=null){
					input.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSound(){
		new MySound();
	}

}
