package gametank.paint.start.orend;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gametank.paint.tool.Constant;

public class GameComplete extends JPanel{

	private int width=Constant.GAME_S_W;
	private int height=Constant.GAME_S_H;
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0,0,width,height);
		g.setColor(Color.YELLOW);
		g.drawString("tonguganle",200,300);
	}
}
