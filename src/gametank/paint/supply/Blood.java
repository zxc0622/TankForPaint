package gametank.paint.supply;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gametank.paint.tool.Constant;

public class Blood {
	private int x;
	private int y;
	private boolean life=true;
	private int cycle=Constant.BLOOD_CYCLE;
	private int bloodValue;
	private int width;
	private int height;
	public Blood(int x, int y,int bloodValue) {
		super();
		this.x = x;
		this.y = y;
		this.bloodValue=bloodValue;
		switch(bloodValue){
			case 1:
				width=height=12;
				break;
			case 2:
				width=height=16;
				break;
		}
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public boolean isLife() {
		return life;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getBloodValue() {
		return bloodValue;
	}
	public void setBloodValue(int bloodValue) {
		this.bloodValue = bloodValue;
	}
	public void draw(Graphics g){
		if(cycle<0) life=false;
		cycle--;
		Color color=g.getColor();
		g.setColor(Color.red);
		g.drawRect(x, y,width,height);
		g.fillOval(x, y,width,height);
		g.setColor(color);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y,12,12);
	}
}
