package gametank.paint.start.orend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import gametank.paint.tool.Constant;

public class GameStartPanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameStartPanel() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.fillRect(0,0,Constant.GAME_S_W,Constant.GAME_S_H);
		view(g);
	}
	
	private int flag=-20;
	
	public void view(Graphics g){
		if(flag>95){
			flag=0;
		}else{
			flag+=15;
		}
		g.setColor(Color.YELLOW);
		g.setFont(new Font("ÀŒÃÂ",Font.BOLD,20));
		g.drawString("º”‘ÿ÷–,«Î…‘∫Ú",230,350);
		for(int i=10;i<100;i+=15){
			if(flag<i){
				g.drawOval(245+(i),360,10,10);
			}else{
				g.fillOval(245+(i),360,10,10);
			}
		}
	}
	
	@Override
	public void run() {
		try {
			for(int i=1;i<15;i++){
				Thread.sleep(500);
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
