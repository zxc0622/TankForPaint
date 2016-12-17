package gametank.paint.entity;

import java.awt.Color;
import java.awt.Graphics;

import gametank.paint.panel.GamePanel;
import gametank.paint.parent.Tank;
import gametank.paint.supply.Blood;

public class MyTank extends Tank{
	
	private int score;
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public MyTank() {}
	public MyTank(int x,int y,int direction,Color color) {
		super(x, y, direction, color);
		setBlomLife(5);
	}
	public MyTank(int x,int y,int direction,Color color,GamePanel gamePanel){
		super(gamePanel, x, y, color, direction);
		setBlomLife(5);
	}
	
	public MyTank(int direction,Color color){
		super(direction, color);
		setBlomLife(5);
	}
	
	public void setInfo(int x,int y,GamePanel panel){
		this.x=x;
		this.y=y;
		this.tc=panel;
	}
	
	public void eatBloob(Blood blood){
		if(getRect().intersects(blood.getRect())){
			blood.setLife(false);
			int i=getBlomLife()+blood.getBloodValue();
			if(i>5)i=5;
			setBlomLife(i);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		drawBlmb(g);
	}
	
	public void drawBlmb(Graphics g){
		Color color=g.getColor();
		g.setColor(Color.RED);
		for(int i=0;i<5;i++){
			if(blomLife-1>=i){
				g.fillRect(600+(i*15), 300,10,5);
			}else{
				g.drawRect(600+(i*15), 300,10,5);
			}
		}
		//画出分数
		g.setColor(Color.BLUE);
		g.drawString("分数:"+score,600,350);
		g.setColor(color);
	}
}
