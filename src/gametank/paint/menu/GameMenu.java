package gametank.paint.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gametank.paint.frame.GameFrame;
import gametank.paint.show.HelpWindow;
import gametank.paint.show.LevelPanel;
import gametank.paint.tool.Constant;
import gametank.paint.tool.Mysound;

public class GameMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GameFrame frame;
	private JMenu menu1,menu2,menu3;
	private JMenuItem menuItem1,menuItem2,menuItem3,menuItem4;
	private JCheckBoxMenuItem sound;
	private LevelPanel levelPanel;

	public GameMenu(GameFrame frame) {
		this.frame=frame;
		menuItem1=new JMenuItem("暂停游戏");
		menu1=new JMenu("游戏");
		menu1.add(menuItem1);

		menuItem2=new JMenuItem("游戏说明");
		menu2=new JMenu("说明");
		menu2.add(menuItem2);

		menuItem3=new JMenuItem("敌人数量加1");
		sound=new JCheckBoxMenuItem("声音");
		//默认开启声音
		sound.setSelected(true);
		menu3=new JMenu("游戏设置");
		menu3.add(menuItem3);
		menu3.add(sound);

		menuItem4=new JMenuItem("开始游戏");
		menu1.add(menuItem4);

		add(menu1);
		add(menu2);
		add(menu3);



		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("stopGame");

		menuItem2.addActionListener(this);
		menuItem2.setActionCommand("gameTheory");

		menuItem3.addActionListener(this);
		menuItem3.setActionCommand("enemyCount");

		menuItem4.addActionListener(this);
		menuItem4.setActionCommand("startGame");

		sound.setActionCommand("openSound");
		sound.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String commed=e.getActionCommand();
		try {
			command(commed);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void command(String commed) throws Exception{
		switch(commed){
		case "gameTheory":
			JOptionPane.showMessageDialog(frame,"A==> 左\nD== > 右\n"
					+ "W==> 上\nS== >下\nJ== >开火","游戏操作方法",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case "stopGame":
			if(frame.getPanel()!=null) frame.getPanel().setRunning(!frame.getPanel().isRunning());
//						frame.getPanel().setVisible(false);
//						frame.remove(frame.getPanel());
			break;
		case "enemyCount":
			new HelpWindow(frame,true);
			break;
		case "startGame":
			if(frame.getResetPanel()==null){
				int result=JOptionPane.showConfirmDialog(frame,"是否重新开始游戏?","温馨提示",
						JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.NO_OPTION||result
						==JOptionPane.CLOSED_OPTION){
					return;
				}
				restart();
				return;
			}
			levelPanel=new LevelPanel(frame,Constant.level);
			new Thread(levelPanel).start();
			frame.remove(frame.getResetPanel());
			//停用线程
			frame.getResetPanel().setLife(false);
			//用于记录下次用户再次点击此按钮时的判断
			frame.setResetPanel(null);
			//重汇看到效果

			//如果用户开始新游戏,那么便停止主界面音乐
			//同时需要因为还没有停止的时候才需要关闭
			if(frame.getSound().getPlayer()!=null){
				frame.getSound().getPlayer().close();
				frame.getSound().getIn().close();
			}

			frame.repaint();
			frame.add(levelPanel);
			//使新的面板起效,类似于 frame.repaint();
			frame.validate();
			break;
		case "openSound":
			boolean s=sound.isSelected();
			if(s){
				Constant.sound=true;
				Mysound mysound=null;
				if(frame.getSound()!=null&&levelPanel==null){
					if(frame.getSound().getPlayer()==null){
						mysound=new Mysound("tank.mp3");
						frame.setSound(mysound);
					}
				}

				if(levelPanel!=null){
					if(s||levelPanel.getSound().getPlayer()==null){
						mysound=new Mysound("start.mp3");
						levelPanel.setSound(mysound);
					}
				}

				//启用音乐
				if(mysound!=null) mysound.start();
			}else{
				Constant.sound=false;
				if(frame.getSound().getPlayer()!=null){
					frame.getSound().getPlayer().close();
					frame.getSound().getIn().close();
				}
				if(levelPanel!=null){
					if(levelPanel.getSound().getPlayer()!=null){
						levelPanel.getSound().getPlayer().close();
						levelPanel.getSound().getIn().close();
					}
				}
			}
			break;
		}
	}

	/**
	 * 用于处理用户选择是否重新开始游戏和不开始的方法
	 */
	public void restart(){
		if(levelPanel!=null&&levelPanel.isLife()){
			frame.remove(levelPanel);
			levelPanel.setLife(false);
			frame.repaint();
			frame.validate();
		}else{
			frame.getPanel().setLife(false);
			frame.remove(frame.getPanel());
			frame.removeKeyListener(frame.getListener());
		}
		//重新开始游戏时血量加满并清空分数
		Constant.TANK.setBlomLife(5);
		Constant.TANK.setScore(0);
		levelPanel=new LevelPanel(frame, Constant.level);
		new Thread(levelPanel).start();
		frame.repaint();
		frame.add(levelPanel);
		frame.validate();
	}
}
