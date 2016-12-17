package gametank.paint.welcome;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import gametank.paint.frame.GameFrame;
import gametank.paint.menu.GameMenu;
import gametank.paint.tool.Constant;
import gametank.paint.tool.MyFont;

public class ResetPanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean life=true;
	private List<Color> colors2=
			Arrays.asList(Color.YELLOW,Color.BLUE,Color.GREEN,Color.RED);
	private static Random random=new Random();
	private int width=Constant.WIDTH;
	private int height=Constant.HEIGHT;
	private int cycle=101;
	private Color[] colors=new Color[]{Color.BLUE,Color.GREEN,Color.YELLOW
			,Color.RED,Color.CYAN,Color.PINK};
	private GameMenu menu;
	private GameFrame frame;
	private int x=110,y=250;
	private boolean di=true;
	private int r_x=200,r_y=340;
	private int move=20;
	public GameFrame getFrame() {
		return frame;
	}
	public boolean isLife() {
		return life;
	}
	
	public void setLife(boolean life) {
		this.life = life;
	}
	public ResetPanel(GameFrame frame) {
		menu=new GameMenu(frame);
		add(menu);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0,0, width, height);
		if(cycle%2==0){
			g.setFont(MyFont.FONT3);
			g.setColor(colors2.get(random.nextInt(4)));
			g.drawString("Ì¹ ¿Ë ´ó Õ½",132,150);
		}
		for(Color color:colors){
			drawTank(g,color);
			x+=60;
		}
		if(cycle==0){
			cycle=101;
		}
		runTank(g,r_x,r_y,Color.YELLOW);
		cycle--;
	}
	
	public void drawTank(Graphics g,Color color){
		g.setColor(color);
		g.fill3DRect(x, y, 5, 26, false);
		g.fill3DRect(x + 5, y + 5, 15, 15, false);
		g.fill3DRect(x + 15, y, 5, 26, false);
		g.fillOval(x + 5, y + 6, 10, 10);
		g.drawLine(x + 10, y + 6, x + 10, y - 2);
		if(x==410){
			x=50;
			changeTankColor();
		}
	}
	
	public void changeTankColor(){
		Color color1=colors[0];
		Color color2=colors[1];
		Color color3=colors[2];
		for(int i=0;i<3;i++){
			colors[i]=colors[5-i];
		}
		colors[5]=color1;
		colors[4]=color2;
		colors[3]=color3;
	}
	
	public void runTank(Graphics g,int x,int y,Color color){
		g.setColor(color);
		if(di){
			g.fill3DRect(x, y, 26, 5, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fillOval(x + 7, y + 5, 10, 10);
			g.fill3DRect(x, y + 15, 26, 5, false);
			g.drawLine(x + 7, y + 10, x + 28, y + 10);
			r_x+=move;
		}else{
			g.fill3DRect(x, y, 26, 5, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fillOval(x + 7, y + 5, 10, 10);
			g.fill3DRect(x, y + 15, 26, 5, false);
			g.drawLine(x + 7, y + 10, x - 2, y + 10);
			r_x-=move;
		}
		if(r_x>350){
			di=false;
		}
		if(r_x<200){
			di=true;
		}
	}

	@Override
	public void run() {
		while(life){
			try {
				Thread.sleep(500);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
