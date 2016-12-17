package gametank.paint.show;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gametank.paint.tool.Constant;
import gametank.paint.tool.MyFont;

public class HelpWindow extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> vals1,vals2,vals3;
	private JLabel main,label1,label2,label3;
	private JButton confirm,cancel;
	private static final int X=Constant.SCREEN_W;
	private static final int Y=Constant.SCREEN_H;
	private static final int width=400;
	private static final int height=400;
	
	private static Integer[] items1=new Integer[6];
	private static Integer[] items2=new Integer[10];
	private static Integer[] items3=new Integer[]{2,3,4};
	
	
	static{
		for(int i=0;i<10;i++){
			items2[i]=i+1;
			if(i>3)
			items1[i-4]=i*5;
		}
	}
	
	public static void main(String[] args) {
		new HelpWindow(null,true);
	}

	public HelpWindow(JFrame frame,boolean b){
		super(frame, b);
		vals1=new JComboBox<Integer>(items1);
		vals2=new JComboBox<Integer>(items2);
		vals3=new JComboBox<Integer>(items3);
		
		cancel=new JButton("取消");
		confirm=new JButton("确定");
		
		main=new JLabel("游戏配置");
		label1=new JLabel("敌人总量:");
		label2=new JLabel("关卡选择:");
		label3=new JLabel("界面敌人:");
		
		setLayout(null);
		
		main.setForeground(Color.RED);
		main.setFont(MyFont.FONT4);
		main.setBounds(80,20,300,50);
		
		label1.setFont(MyFont.FONT2);
		label1.setBounds(50,100,180,30);
		vals1.setBounds(250,100,50,30);
		
		label2.setFont(MyFont.FONT2);
		label2.setBounds(50,150,180,30);
		vals2.setBounds(250,150,50,30);

		label3.setFont(MyFont.FONT2);
		label3.setBounds(50,200,180,30);
		vals3.setBounds(250,200,50,30);
		
		
		cancel.setFont(MyFont.FONT1);
		cancel.setBounds(50,300,100,20);
		confirm.setFont(MyFont.FONT1);
		confirm.setBounds(210,300,100,20);
		
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		confirm.setActionCommand("confirm");
		
		add(main);
		add(confirm);
		add(cancel);
		add(vals1);
		add(vals2);
		add(label1);
		add(label2);
		add(label3);
		add(vals3);
		//背景色设置为透明的
//		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(width,height);
		setLocation(X-(width/2),Y-(height/2));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("confirm")){
			Constant.level=(Integer)(vals2.getSelectedItem());
			Constant.enemy_count=(Integer)(vals1.getSelectedItem());
			Constant.E_COUNT=(Integer)(vals3.getSelectedItem());
		}
		this.dispose();
	}

}
