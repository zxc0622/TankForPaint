package gametank.paint.start.orend;

import java.awt.BorderLayout;

import javax.swing.JProgressBar;
import javax.swing.JWindow;

import gametank.paint.frame.GameFrame;
import gametank.paint.tool.Constant;
/**
 *	游戏进入类,加载显示效果
 */
public class GameStart extends JWindow implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBar;
	private Thread thread;
	private static int values[]=new int[]{1,5,11,18,26,37,45,68,75,88,93,98,99,100};
	
	public static void main(String[] args) {
		GameStartPanel gameStartPanel=new GameStartPanel();
		new Thread(new GameStart(gameStartPanel)).start();
	}
	
	public GameStart(GameStartPanel gameStartPanel) {
		
		thread=new Thread(gameStartPanel);
		thread.start();
		
		progressBar=new JProgressBar();
		progressBar.setStringPainted(true);
		
		this.setLayout(new BorderLayout());
		setSize(Constant.GAME_S_W,Constant.GAME_S_H);
		setLocation(Constant.SCREEN_W-(Constant.GAME_S_W/2),Constant.SCREEN_H-(Constant.GAME_S_H/2));
		add(progressBar,BorderLayout.SOUTH);
		add(gameStartPanel,BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void run() {
		for(int i=0;i<values.length;i++){
			try {
				Thread.sleep(500);
				progressBar.setValue(values[i]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
			thread.join();
			new GameFrame();
			this.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
