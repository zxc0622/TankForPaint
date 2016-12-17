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
		menuItem1=new JMenuItem("��ͣ��Ϸ");
		menu1=new JMenu("��Ϸ");
		menu1.add(menuItem1);

		menuItem2=new JMenuItem("��Ϸ˵��");
		menu2=new JMenu("˵��");
		menu2.add(menuItem2);

		menuItem3=new JMenuItem("����������1");
		sound=new JCheckBoxMenuItem("����");
		//Ĭ�Ͽ�������
		sound.setSelected(true);
		menu3=new JMenu("��Ϸ����");
		menu3.add(menuItem3);
		menu3.add(sound);

		menuItem4=new JMenuItem("��ʼ��Ϸ");
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
			JOptionPane.showMessageDialog(frame,"A==> ��\nD== > ��\n"
					+ "W==> ��\nS== >��\nJ== >����","��Ϸ��������",
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
				int result=JOptionPane.showConfirmDialog(frame,"�Ƿ����¿�ʼ��Ϸ?","��ܰ��ʾ",
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
			//ͣ���߳�
			frame.getResetPanel().setLife(false);
			//���ڼ�¼�´��û��ٴε���˰�ťʱ���ж�
			frame.setResetPanel(null);
			//�ػ㿴��Ч��

			//����û���ʼ����Ϸ,��ô��ֹͣ����������
			//ͬʱ��Ҫ��Ϊ��û��ֹͣ��ʱ�����Ҫ�ر�
			if(frame.getSound().getPlayer()!=null){
				frame.getSound().getPlayer().close();
				frame.getSound().getIn().close();
			}

			frame.repaint();
			frame.add(levelPanel);
			//ʹ�µ������Ч,������ frame.repaint();
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

				//��������
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
	 * ���ڴ����û�ѡ���Ƿ����¿�ʼ��Ϸ�Ͳ���ʼ�ķ���
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
		//���¿�ʼ��ϷʱѪ����������շ���
		Constant.TANK.setBlomLife(5);
		Constant.TANK.setScore(0);
		levelPanel=new LevelPanel(frame, Constant.level);
		new Thread(levelPanel).start();
		frame.repaint();
		frame.add(levelPanel);
		frame.validate();
	}
}
