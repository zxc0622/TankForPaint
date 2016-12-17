package gametank.paint.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import gametank.paint.frame.GameFrame;
import gametank.paint.panel.GamePanel;
import gametank.paint.tool.Constant;
import gametank.paint.tool.CurrentLevel;
import gametank.paint.tool.Mysound;

public class LevelPanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width=Constant.WIDTH;
	private int height=Constant.HEIGHT;
	private static Font font=new Font("华文新魏",Font.BOLD,30);
	private int level;
	private boolean life=true;
	private int cycle=5;
	private GameFrame frame;
	private Mysound sound;
	public LevelPanel(GameFrame frame,int level) {
		this.level=level;
		this.frame=frame;
		if(Constant.sound){
			sound=new Mysound("start.mp3");
			sound.start();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0,0,width,height);
		g.setColor(Color.YELLOW);
		if(cycle%2==0){
			g.setFont(font);
			g.drawString("第"+level+"关",225,210);
		}
		cycle--;
	}

	@Override
	public void run() {
		while(life){
			try {
				Thread.sleep(600);
				if(cycle<=0&&life){
					newPanel();
				}else{
					repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void  newPanel(){
		try {
			if(frame.getPanel()!=null){
				//停止不再使用的面板线程
				frame.getPanel().setLife(false);
				frame.setPanel(null);
				frame.setListener(null);
			}
			String level=CurrentLevel.map.get("level")+String.valueOf(Constant.level);
			@SuppressWarnings("unchecked")
			Class<GamePanel> panel=(Class<GamePanel>) Class.forName(level);
			GamePanel panelLevle=panel.newInstance();
			panelLevle.setFrame(frame);
			new Thread(panelLevle).start();
			frame.remove(this);
			
			//停用当前线程
			this.life=false;
			
			//设置面板和监听事件
			frame.setPanel(panelLevle);
			frame.setListener(panelLevle.new TankKeyListener());
			
			//添加面板和监听事件
			frame.add(panelLevle);
			frame.addKeyListener(frame.getListener());
			
			//显示删除和添加后的效果
			frame.repaint();
			frame.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Mysound getSound() {
		return sound;
	}
	public void setSound(Mysound sound) {
		this.sound = sound;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public boolean isLife() {
		return life;
	}
}
