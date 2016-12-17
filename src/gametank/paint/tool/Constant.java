package gametank.paint.tool;

import java.awt.Color;
import java.awt.Toolkit;

import gametank.paint.entity.MyTank;

public class Constant {
	
	/**
	 * 游戏运行界面的大小
	 */
	public static final int WIDTH=550;
	public static final int HEIGHT=450;
	
	/**
	 * 当前电脑屏幕大小更一半的取值
	 */
	
	public static final int SCREEN_W=Toolkit.getDefaultToolkit().getScreenSize().width/2;
	public static final int SCREEN_H=Toolkit.getDefaultToolkit().getScreenSize().height/2;
	
	/**
	 * 游戏启动界面的大小调整(加载界面)
	 */
	
	public static final int GAME_S_W=600;
	public static final int GAME_S_H=400;
	
	
	/**
	 * 游戏界面的大小
	 */
	public static final int GAME_WIDTH=800;
	public static final int GAME_HEIGHT=600;
	
	/**
	 * 血包存在的周期,数值越大存在越久
	 */
	public static final int BLOOD_CYCLE=120;
	
	/**
	 * 敌人朝一个方向行进多久的距离
	 */
	public static final int SPEED_LONG=20;
	/**
	 * 敌人开火的概率大小,
	 * 至少为10,越大于10概率越大
	 */
	public static final int FIRE_PRO=20;
	
	/**
	 * 出现在战场上的最多坦克数量
	 */
	public static int E_COUNT=4;
	
	/**
	 * 默认关卡为第一关
	 */
	public static int level=1;
	
	/**
	 * 默认敌人数量为 20
	 */
	public static int enemy_count=20;
	/**
	 * 标记声音是否开启,默认开启
	 */
	public static boolean sound=true;
	/**
	 * 用于记录多久出现血包
	 * 值越大出现的间隔越大(即越久才出现)
	 * 5500 相当于 4分钟左右
	 */
	public static final int SHOW_BLOOD=5500;
	
	
	/**
	 * 
	 */
	public static final MyTank TANK=new MyTank(1,Color.YELLOW);
}
