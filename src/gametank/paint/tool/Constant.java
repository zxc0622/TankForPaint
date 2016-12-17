package gametank.paint.tool;

import java.awt.Color;
import java.awt.Toolkit;

import gametank.paint.entity.MyTank;

public class Constant {
	
	/**
	 * ��Ϸ���н���Ĵ�С
	 */
	public static final int WIDTH=550;
	public static final int HEIGHT=450;
	
	/**
	 * ��ǰ������Ļ��С��һ���ȡֵ
	 */
	
	public static final int SCREEN_W=Toolkit.getDefaultToolkit().getScreenSize().width/2;
	public static final int SCREEN_H=Toolkit.getDefaultToolkit().getScreenSize().height/2;
	
	/**
	 * ��Ϸ��������Ĵ�С����(���ؽ���)
	 */
	
	public static final int GAME_S_W=600;
	public static final int GAME_S_H=400;
	
	
	/**
	 * ��Ϸ����Ĵ�С
	 */
	public static final int GAME_WIDTH=800;
	public static final int GAME_HEIGHT=600;
	
	/**
	 * Ѫ�����ڵ�����,��ֵԽ�����Խ��
	 */
	public static final int BLOOD_CYCLE=120;
	
	/**
	 * ���˳�һ�������н���õľ���
	 */
	public static final int SPEED_LONG=20;
	/**
	 * ���˿���ĸ��ʴ�С,
	 * ����Ϊ10,Խ����10����Խ��
	 */
	public static final int FIRE_PRO=20;
	
	/**
	 * ������ս���ϵ����̹������
	 */
	public static int E_COUNT=4;
	
	/**
	 * Ĭ�Ϲؿ�Ϊ��һ��
	 */
	public static int level=1;
	
	/**
	 * Ĭ�ϵ�������Ϊ 20
	 */
	public static int enemy_count=20;
	/**
	 * ��������Ƿ���,Ĭ�Ͽ���
	 */
	public static boolean sound=true;
	/**
	 * ���ڼ�¼��ó���Ѫ��
	 * ֵԽ����ֵļ��Խ��(��Խ�òų���)
	 * 5500 �൱�� 4��������
	 */
	public static final int SHOW_BLOOD=5500;
	
	
	/**
	 * 
	 */
	public static final MyTank TANK=new MyTank(1,Color.YELLOW);
}
