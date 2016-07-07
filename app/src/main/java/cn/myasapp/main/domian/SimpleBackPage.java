package cn.myasapp.main.domian;



import com.cp.mylibrary.utils.LogCp;

import cn.myasapp.main.ui.TestFragment;


/**
 * 返回页的基本信息注册 (其实就是将原本会在Manifest中注册的Activity转成Fragment在这里注册)
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public enum SimpleBackPage {

	VIEW_PAGE(1, TestFragment.class) ;


	
	


	private Class<?> clazz;
	private int value;

	private SimpleBackPage(int value, Class<?> cls) {
		this.clazz = cls;
		this.value = value;

	}

	public int getValue() {
		return value;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public static Class<?> getPageByValueClass(int val) {
		for (SimpleBackPage p : values()) {
			if (p.getValue() == val)
				return p.getClass();
		}
		return null;
	}

	public static SimpleBackPage getPageByValue(int val) {

	 LogCp.i(LogCp.CP,SimpleBackPage.class + " values().length  " + values().length +"  go go ," + val) ;
		for (SimpleBackPage p : values()) {

			LogCp.i(LogCp.CP,SimpleBackPage.class + " p.getValue()  "   + p.getValue()) ;


			if (p.getValue() == val)
				return p;
		}
		return null;
	}

}
