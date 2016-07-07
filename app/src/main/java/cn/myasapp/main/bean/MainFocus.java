package cn.myasapp.main.bean;


import com.cp.mylibrary.bean.MyEntity;

/**
 * 主页的关注 实体
 * 
 * @author Administrator
 * 
 */
public class MainFocus extends MyEntity {

	

	//  Id
	private int shopId;
	// shopName
	private String shopName;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
