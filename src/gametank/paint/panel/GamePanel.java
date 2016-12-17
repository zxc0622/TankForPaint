package gametank.paint.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import gametank.paint.entity.Bullet;
import gametank.paint.entity.CommonWall;
import gametank.paint.entity.EnemyTank;
import gametank.paint.entity.Light;
import gametank.paint.entity.MetalWall;
import gametank.paint.entity.MyTank;
import gametank.paint.frame.GameFrame;
import gametank.paint.parent.Bomb;
import gametank.paint.start.orend.GameComplete;
import gametank.paint.start.orend.GameEndPanel;
import gametank.paint.supply.Blood;
import gametank.paint.tool.Constant;
import gametank.paint.tool.CurrentLevel;
import gametank.paint.tool.TankType;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private int width=Constant.WIDTH;
	private int height=Constant.HEIGHT;
	
	//��ȡframe
	private GameFrame frame;
	
	//���ڼ�¼ÿ�ֵ��˸��ж��ٵ�map
	private Map<TankType,Integer> map=new HashMap<TankType,Integer>();

	private static int b_l=Constant.SHOW_BLOOD;
	
	//�����ϳ��ֵĵ�������,�����û��Լ�ѡ��
	public int count=Constant.E_COUNT;
	//��ǵ�ǰ����Ƿ�������״̬��
	private boolean running=true;
	//��ǵ�ǰ����̻߳�Ҫ��������
	private boolean life=true; 
	
	//�������������,��ȡ��ͬ���͵�̹��
	private static Random random=new Random();
	
	//���ڷ���̹�˳��ֵ�λ��,Ŀǰ�˷�ʽ���ܻ����bug
	private static int [] location =new int[]{0,160,330,520};
	
	public static MyTank tank;
	protected List<EnemyTank> enemyTanks;
	protected List<CommonWall> commonWalls;
	protected List<MetalWall> metalWalls;
	protected List<Bomb> Bombs;
	protected Blood blood;
	//�ؿ��Ƿ����,Ĭ��Ϊ��
	protected boolean end;
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}
	public static MyTank getTank() {
		return tank;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public Blood getBlood() {
		return blood;
	}
	public void setBlood(Blood blood) {
		this.blood = blood;
	}
	public List<Bomb> getBigBombs() {
		return Bombs;
	}
	public List<EnemyTank> getEnemyTanks() {
		return enemyTanks;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public GameFrame getFrame() {
		return frame;
	}
	protected Light light=null;
	public GamePanel() {
		int flag=0;
		tank=Constant.TANK;
		tank.setInfo(120,380,this);
		light=new Light(tank.getX(),tank.getY());
		enemyTanks=new LinkedList<EnemyTank>();
		Bombs=new ArrayList<Bomb>();
		commonWalls=new ArrayList<CommonWall>();
		metalWalls=new ArrayList<MetalWall>();
		for(int i=0;i<Constant.enemy_count;i++){
			TankType tankType=null;
			switch(random.nextInt(4)){
				case 0:
					tankType=TankType.EASYTANK;
					break;
				case 1:
					tankType=TankType.SIMPLETANK;
					break;
				case 2:
					tankType=TankType.HEAVYTANK;
					break;
				case 3:
					tankType=TankType.HARDTANK;
					break;
			}
			int d=random.nextInt(4)+1;
			EnemyTank enemyTank=new EnemyTank(location[flag],0,tankType,d);
			if(flag==3){
				flag=0;
			}else{
				flag++;
			}
			enemyTank.setTc(this);
			enemyTanks.add(enemyTank);
			if(map.get(tankType)==null){
				map.put(tankType,1);
			}else{
				map.put(tankType,map.get(tankType)+1);
			}
		}
		CurrentLevel.enemys=map;
		}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0,width,height);
		if(light.isLife()) light.draw(g,tank);
		if(!enemyTanks.isEmpty()){
			drawEnemyCount(g);
		}
		
		if(b_l==1000){
			b_l=Constant.SHOW_BLOOD;
			int b=random.nextInt(150)+100;
			int bld=random.nextInt(2)+1;
			blood=new Blood(b,b,bld);
		}
		if(blood!=null){
			if(blood.isLife()){
				blood.draw(g);
				tank.eatBloob(blood);
			}else{
				setBlood(null);
			}
		}else{
			b_l--;
		}
		
		if(tank!=null&&tank.isLife()){
			tank.draw(g);
			tank.hitTank(enemyTanks);
			for(int i=0;i<tank.getBullets().size();i++){
				Bullet bullet=tank.getBullets().get(i);
				if(bullet.isLife()){
					bullet.draw(g);
					bullet.hitCommWall(commonWalls);
					bullet.hitMetal(metalWalls);
					bullet.hitTank(enemyTanks);
				}else{
					tank.getBullets().remove(bullet);
				}
			}
		}
		for(int i=0;i<(enemyTanks.size()>count?count:enemyTanks.size());i++){
			EnemyTank enemyTank=enemyTanks.get(i);
			if(enemyTank.isLife()){
				enemyTank.draw(g);
				enemyTank.hitTank(enemyTanks);
				if(tank!=null&&tank.isLife()){
					enemyTank.hitMe(tank);
				}
				for(int j=0;j<enemyTank.getBullets().size();j++){
					Bullet bullet=enemyTank.getBullets().get(j);
					if(bullet.isLife()){
						bullet.draw(g);
						bullet.hitCommWall(commonWalls);
						bullet.hitMetal(metalWalls);
						if(tank!=null&&tank.isLife()){
						bullet.hitMe(tank);
						}
					}else{
						enemyTank.getBullets().remove(bullet);
					}
				}
			}
		}
		
		for(int k=0;k<commonWalls.size();k++){
			CommonWall commonWall=commonWalls.get(k);
			if(commonWall.isLife()){
				commonWall.draw(g);
				if(tank!=null&&tank.isLife()&&tank.hitWall(commonWall)){
					tank.changeDirection();
				}
			}
		}
		for(int j=0;j<metalWalls.size();j++){
			MetalWall metalWall=metalWalls.get(j);
			if(metalWall.isLife()){
				metalWall.draw(g);
				if(tank!=null&&tank.isLife()&&tank.hitWall(metalWall)){
					tank.changeDirection();
				}
			}
		}
		
		for(int l=0;l<(enemyTanks.size()>count?count:enemyTanks.size());l++){
			EnemyTank enemyTank=enemyTanks.get(l);
			if(enemyTank.isLife()){
				for(int m=0;m<commonWalls.size();m++){
					CommonWall commonWall=commonWalls.get(m);
					if(enemyTank.hitWall(commonWall)){
						enemyTank.changeDirection();
					}
				}
				for(int n=0;n<metalWalls.size();n++){
					MetalWall metalWall=metalWalls.get(n);
					if(enemyTank.hitWall(metalWall)){
						enemyTank.changeDirection();
					}
				}
			}
		}
		
		for(int i=0;i<Bombs.size();i++){
			Bomb bigBomb=Bombs.get(i);
			if(bigBomb.isLife()){
				bigBomb.draw(g);
			}else{
				Bombs.remove(bigBomb);
			}
		}
	}
	
	
	public void drawEnemyCount(Graphics g){
		Color color=g.getColor();
		g.setColor(Color.GREEN);
		int wei=40;
		int flag=1;
		int hei=20;
		for(int i=1;i<enemyTanks.size()+1;i++){
			if(flag==6){
				wei+=20;
				hei=20;
				flag=1;
			}
			g.fillRect(600+(hei*flag),wei,10,5);
			flag++;
		}
		g.setColor(color);
	}
	
	/**
	 * �ڲ����̼�����,����ֱ��ʹ���ⲿ��ĳ�Ա
	 * @author ZXC
	 *
	 */
	public class TankKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(running){
				//��Ϸ��ͣʱ�������˶�
				tank.direction(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			tank.release(e);
		}
		
	}

	@Override
	public void run() {
		while(life){
			try {
				Thread.sleep(50);
				if(running){
					//��Ϸ����ʱ�Ž����ػ�,���˶�
					repaint();
				}
				if(!end&&enemyTanks.isEmpty()){
					Constant.level=Constant.level+1;
					int level=Constant.level;
					end=true;
					if(level>10){
						/**
						 * ���һ�ص���Ϸ������ʱ������
						 */
						Constant.level=10;
						new GameEnd(this).start();
					}else{
						new End(this).start();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 *  ���ڽ�����ǰ�ؿ���Ϸ���߳���,д���ڲ��Ƿ�����õ�ǰ����life�����Ա��������߳�
	 */
	class End extends Thread{
		private GamePanel panel;
		public End(GamePanel panel) {
			this.panel=panel;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				life=false;
				GameEndPanel endPanel=new GameEndPanel(frame);
//				��ʱ����Ϊ�߳��� new Thread(endPanel).start();
				//ɾ����ǰ�ؿ����,��ӽ�����������ʾ
				frame.remove(this.panel);
				frame.repaint();
				frame.add(endPanel);
				frame.validate();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * ������Ϸ����ʱ���õ��߳�,�߳���������������Ϸͨ�ص����
	 */
	class GameEnd extends Thread{
		private GamePanel panel;
		public GameEnd(GamePanel panel) {
			this.panel=panel;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				life=false;
				GameComplete complete=new GameComplete();
				//ɾ����ǰ�ؿ����,��ӽ�����������ʾ
				frame.remove(this.panel);
				frame.repaint();
				frame.add(complete);
				frame.validate();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
