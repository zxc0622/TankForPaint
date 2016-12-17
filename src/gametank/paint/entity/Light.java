package gametank.paint.entity;

import java.awt.Color;
import java.awt.Graphics;

import gametank.paint.parent.Tank;

public class Light {
	private int x;
	private int y;
	private int cycle=80;
	private boolean life=true;
	
	public void draw(Graphics g,Tank tank){
		Color color=g.getColor();
		g.setColor(Color.WHITE);
		g.drawOval(x, y,35,35);
		g.setColor(color);
		move(tank.getX(),tank.getY(),tank.getDirection());
		cycle--;
		if(cycle==0) life=false;
	}
	
	public void move(int x,int y,int direction){
		switch(direction){
			case 1:
			case 2:
				x=x-8;
				y=y-5;
			break;
			case 3:
			case 4:
				x=x-4;
				y=y-8;
				break;
		}
		setX(x);
		setY(y);
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
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public Light(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Light() {

	}
}
