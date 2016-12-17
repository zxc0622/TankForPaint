package gametank.paint.frame;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import gametank.paint.menu.GameMenu;
import gametank.paint.panel.GamePanel;
import gametank.paint.tool.Constant;
import gametank.paint.tool.Mysound;
import gametank.paint.welcome.ResetPanel;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GameMenu menu;
	private GamePanel panel;
	private KeyListener listener;
	private ResetPanel resetPanel;
	private Mysound sound;
	
	public static void main(String[] args) {
		new GameFrame();
	}

	public GameFrame() {
		setTitle("javaTank with Paint");
		setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		setLocation(Constant.SCREEN_W-(Constant.GAME_WIDTH/2),Constant.SCREEN_H-(Constant.GAME_HEIGHT/2));
		setResizable(false);
		menu=new GameMenu(this);
		resetPanel=new ResetPanel(this);
		new Thread(resetPanel).start();
		add(resetPanel);
		setJMenuBar(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//播放注见面的音乐,只播放一次
		sound=new Mysound("tank.mp3");
		sound.start();
	}
	
	public ResetPanel getResetPanel() {
		return resetPanel;
	}
	public void setResetPanel(ResetPanel resetPanel) {
		this.resetPanel = resetPanel;
	}
	public void setListener(KeyListener listener) {
		this.listener = listener;
	}
	public KeyListener getListener() {
		return listener;
	}
	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}
	public GamePanel getPanel() {
		return panel;
	}
	public Mysound getSound() {
		return sound;
	}
	public void setSound(Mysound sound) {
		this.sound = sound;
	}
}
