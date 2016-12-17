package gametank.paint.start.orend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

import gametank.paint.frame.GameFrame;
import gametank.paint.show.LevelPanel;
import gametank.paint.tool.Constant;
import gametank.paint.tool.CurrentLevel;
import gametank.paint.tool.MyFont;
import gametank.paint.tool.TankType;

public class GameEndPanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width=Constant.GAME_S_W;
	private int height=Constant.GAME_S_H;
	private Map<TankType,Integer> maps=CurrentLevel.enemys;
	private static Map<TankType,Color> getColor;
	private int x=150;
	private int y=100;
	private GameFrame frame;
	private int value;
//	private int cycle=100;
//	private boolean life=true;
	
	/**
	 * ��̬������ʼ���������Ƶ�̹�˶�Ӧ����ɫ
	 */
	static{
		getColor=new LinkedHashMap<TankType,Color>();
		getColor.put(TankType.EASYTANK,Color.RED);
		getColor.put(TankType.SIMPLETANK,Color.GREEN);
		getColor.put(TankType.HEAVYTANK,Color.CYAN);
		getColor.put(TankType.HARDTANK,Color.PINK);
	}
	
	/**
	 * ���������ܵ÷�
	 */
	{
		for(Map.Entry<TankType,Integer> entry:maps.entrySet()){
			TankType tankType=entry.getKey();
			Integer integer=maps.get(tankType);
			if(integer==null){
				integer=0;
			}
			value+=integer*tankType.getSCORCE();
		}
	}
	
	public GameEndPanel(GameFrame frame){
		this.frame=frame;
		frame.addKeyListener(this);
	}
	
	public GameEndPanel() {
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0,0,width,height);
		Font font=g.getFont();
		g.setFont(MyFont.FONT3);
		g.setColor(Color.GREEN);
		g.drawString("�������",190,60);
		g.setFont(font);
		drawTank(g, getColor);
		g.setFont(MyFont.FONT5);
		g.setColor(Color.YELLOW);
		g.drawString("�÷�Ϊ: "+value+"��",200,330);
		g.setColor(Color.BLUE);
		g.setFont(MyFont.FONT6);
		g.drawString("���س�������", 250,350);
		g.drawString("(Enter��)", 270,375);
	}
	
	public void drawTank(Graphics g,Map<TankType,Color> map){
		for(Map.Entry<TankType,Color> entry:map.entrySet()){
			TankType tankType=entry.getKey();
			Integer integer=maps.get(tankType);
	    	g.setColor(entry.getValue());
			g.fill3DRect(x, y, 5, 26, false);
			g.fill3DRect(x + 5, y + 5, 15, 15, false);
			g.fill3DRect(x + 15, y, 5, 26, false);
			g.fillOval(x + 5, y + 6, 10, 10);
			g.drawLine(x + 10, y + 6, x + 10, y - 2);
			if(integer==null){
				integer=0;
			}
			g.drawString("X "+integer, x+130, y+20);
			g.drawString("X "+tankType.getSCORCE(),x+250, y+20);
			y+=50;
			}
		if(y>=300){
			y=100;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10){
			//�Ƴ�̹�˱��������¼�,��Ȼÿ��һ�ػ��ǰһ�ض෢һ���ӵ�
			frame.removeKeyListener(frame.getListener());
			//�Ƴ������¼�,��Ȼ���ظ�������
			frame.removeKeyListener(this);
			frame.remove(this);
			
			LevelPanel levelPanel=new LevelPanel(frame,Constant.level);
			new Thread(levelPanel).start();
			frame.add(levelPanel);
			
			frame.repaint();
			frame.validate();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
}
