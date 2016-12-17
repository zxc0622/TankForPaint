package gametank.paint.parent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import gametank.paint.entity.Bullet;
import gametank.paint.entity.EnemyTank;
import gametank.paint.panel.GamePanel;

public class Tank {
	protected GamePanel tc;
	protected int x;
	protected int y;
	//标记按钮是否被按下，暂时使用这个来做,以后有更好的可以修改(这样并不符合面向对象)
	protected boolean moveable;
	protected int oldX, oldY;
	protected int speed = 4;
	protected Color color;
	protected boolean life = true;
	protected int direction;
	protected int blomLife;
	protected Vector<Bullet> bullets = new Vector<Bullet>();

	public int getX() {
		return x;
	}

	public GamePanel getTc() {
		return tc;
	}

	public void setTc(GamePanel tc) {
		this.tc = tc;
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

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getBlomLife() {
		return blomLife;
	}
	
	public void setBlomLife(int blomLife) {
		this.blomLife = blomLife;
	}

	public Tank(int x, int y, int direction, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.color = color;
	}
	
	public Tank(int x,int y,int direction){
		this.x=x;
		this.y=y;
		this.direction=direction;
	}

	public Tank(GamePanel tc, int x, int y, Color color, int direction) {
		this(x, y, direction, color);
		this.tc = tc;
	}
	
	public Tank(int direction,Color color){
		this.direction=direction;
		this.color=color;
	}

	public Tank() {

	}

	public void draw(Graphics g) {
		drawTankWith(g);
		move();
	}

	public void drawTankWith(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		switch (this.direction) {
		case 1:
			g.fill3DRect(x, y, 5, 26, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fill3DRect(x + 15, y, 5, 26, false);
			g.fillOval(x + 5, y + 6, 10, 10);
			g.drawLine(x + 10, y + 6, x + 10, y - 2);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 26, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fill3DRect(x + 15, y, 5, 26, false);
			g.fillOval(x + 5, y + 6, 10, 10);
			g.drawLine(x + 10, y + 6, x + 10, y + 27);
			break;
		case 3:
			g.fill3DRect(x, y, 26, 5, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fillOval(x + 7, y + 5, 10, 10);
			g.fill3DRect(x, y + 15, 26, 5, false);
			g.drawLine(x + 7, y + 10, x - 2, y + 10);
			break;
		case 4:
			g.fill3DRect(x, y, 26, 5, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fillOval(x + 7, y + 5, 10, 10);
			g.fill3DRect(x, y + 15, 26, 5, false);
			g.drawLine(x + 7, y + 10, x + 28, y + 10);
			break;
		}
		g.setColor(c);
	}

	public boolean hitWall(Wall wall) {
		return wall.getRect().intersects(getRect());
	}

	public void move() {
		this.oldX = x;
		this.oldY = y;
		switch (this.direction) {
		case 1:
			if (y > 2 && moveable)
				y -= speed;
			break;
		case 2:
			if (y < 425 && moveable)
				y += speed;
			break;
		case 3:
			if (x > 2 && moveable)
				x -= speed;
			break;
		case 4:
			if (x < 525 && moveable)
				x += speed;
			break;
		}
	}

	public void direction(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_J) {
			fire();
		}else{
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				this.setDirection(3);
				moveable=true;
				break;
			case KeyEvent.VK_D:
				this.setDirection(4);
				moveable=true;
				break;
			case KeyEvent.VK_W:
				this.setDirection(1);
				moveable=true;
				break;
			case KeyEvent.VK_S:
				this.setDirection(2);
				moveable=true;
				break;
			}
		}
	}
	
	public void release(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_W:
		case KeyEvent.VK_D:
//		case KeyEvent.VK_J:
			moveable=false;
		}
	}

	public Rectangle getRect() {
		int width = 25;
		int height = 26;
		switch (this.direction) {
		case 3:
		case 4:
			width = 26;
			height = 25;
			break;
		}
		return new Rectangle(x, y, width, height);
	}

	public void fire(){
		 if(bullets.size()>5||!life) return;
			Bullet bullet=null;
//			setBlomLife(blomLife-=1);
			switch(this.direction){
				case 1:
					bullet=new Bullet(x+10, y-3, direction);
					break;
				case 2:
					bullet=new Bullet(x+10, y+26, direction);
					break;
				case 3:
					bullet=new Bullet(x-3, y+10, direction);
					break;
				case 4:
					bullet=new Bullet(x+26, y+10, direction);
					break;
			}
			bullet.setTc(tc);
			bullets.add(bullet);
	};
	public void leeBolb(){
		this.blomLife--;
	}
	
	public boolean hitTank(List<EnemyTank> enemyTanks){
		int flag=enemyTanks.size()>tc.count?tc.count:enemyTanks.size();
		for(int i=0;i<flag;i++){
			EnemyTank enemyTank=enemyTanks.get(i);
			if(this!=enemyTank){
				if(getRect().intersects(enemyTank.getRect())){
					changeDirection();
					return true;
				}
			}
		}
		return false;
	}
	public boolean hitMe(Tank myTank){
				if(getRect().intersects(myTank.getRect())){
					changeDirection();
					return true;
				}
		return false;
	}
	
	public void changeDirection(){
		this.x=this.oldX;
		this.y=this.oldY;
	}
}
