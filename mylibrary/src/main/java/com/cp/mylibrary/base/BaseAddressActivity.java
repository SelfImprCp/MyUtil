package com.cp.mylibrary.base;

import com.cp.mylibrary.res.AreaRes;
import com.cp.mylibrary.utils.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BaseAddressActivity extends MyBaseActivity {

	/**
	 * 所有省
	 */
	protected String[] mProvinceDatas;
	
	 /**
	  * 所有市
	  */
	  
	protected String[] mCityDatas;
	
	/**
	 * key - 省 value - 所有的省
	 * 
	 */
	//protected Map<String, List<AreaBean>> mProvinceDatasMap = new HashMap<String, List<AreaBean>>();

	/**
	 * key - 省 value - 当前省下所有的 市
	 * 
	 */
	protected Map<String, List<AreaBean>> mCitisDatasMap = new HashMap<String, List<AreaBean>>();
	/**
	 * key - 市 values - 当前市下所有的 区
	 */
	protected Map<String, List<AreaBean>> mDistrictDatasMap = new HashMap<String, List<AreaBean>>();

	/**
	 * key - 区 values - 邮编
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	protected String mCurrentDistrictName = "";

	/**
	 * 当前区的邮政编码
	 */
	protected String mCurrentZipCode = "";

	/**
	 * 解析省市区的XML数据
	 */

	protected void initProvinceDatas() {

		String strAreaJson = GsonUtil.getJsonForFile(mContext, "area_json.txt");

		AreaRes areaRes = GsonUtil.jsonStrToBean(strAreaJson, AreaRes.class);

		// 所有的省
		List<AreaBean> provincesList = areaRes.getResult().getProvinces();
		// 所有的市
		List<AreaBean> citysList = areaRes.getResult().getCitys();
		// 所有的区
		List<AreaBean> areasList = areaRes.getResult().getAreas();

		// 所有的省
		// List<AreaBean> newProvincesList = new ArrayList<AreaBean>();


//		LogCp.i(LogCp.CP, BaseAddressActivity.class + " 多少省  ，，"
//				+ provincesList.size() + " ,,, 多少个市 ，， " + citysList.size()
//				+ " ,, 多少区，， "
//
//				+ areasList.size());

		// List<ProvinceModel> provinceList = null;
		// AssetManager asset = getAssets();

		// InputStream input = asset.open("province_data.xml");
		// 创建一个解析xml的工厂对象
		// SAXParserFactory spf = SAXParserFactory.newInstance();
		// 解析xml
		// SAXParser parser = spf.newSAXParser();
		// XmlParserHandler handler = new XmlParserHandler();
		// parser.parse(input, handler);
		// input.close();

		
		
		// 所有省的名字
		mProvinceDatas = new String[provincesList.size()];
		
		
		//所有市的名字
		mCityDatas = new String[citysList.size()];

		for (int i = 0; i < provincesList.size(); i++) {
			AreaBean province = provincesList.get(i);

			// 此级的id
			int proID = province.getId();

			mProvinceDatas[i] = provincesList.get(i).getRname();
			// mProvinceDatas[province.getRname()];
			// 某个省下所有的市
			List<AreaBean> newCitysList = new ArrayList<AreaBean>();
	
			// 把市放到省里
			for(int j = 0;j<citysList.size();j++)
			{
		 		
				AreaBean city = citysList.get(j);
				mCityDatas[j]=city.getRname();
				// 上级的id 和此类中的爸爸级ID相同，说明这个当前市是当前省下的
				if (proID == city.getPid()) {
						newCitysList.add(city);

					// 把区放到市里面
					int cityID = city.getId();
					
					// 某个市下所有的区
					List<AreaBean> newAreasList = new ArrayList<AreaBean>();
				
					
					for (AreaBean area : areasList) {
						if (cityID == area.getPid()) {
							
					
							newAreasList.add(area);

						}

					}
					
//					LogCp.i(LogCp.CP,
//							BaseAddressActivity.class + " 当前的市  " +city.getRname() +   "当前市下有多少区：" +newAreasList.size());

					mDistrictDatasMap.put(city.getRname(), newAreasList);
				}
			}
			
			
		//	LogCp.i(LogCp.CP,
			//		BaseAddressActivity.class + " 当前的省  " +province.getRname() +   "当前省下有多少市" +newCitysList.size());

			mCitisDatasMap.put(province.getRname(), newCitysList);

		}

		// 获取解析出来的数据
		// provinceList = handler.getDataList();
		// */ 初始化默认选中的省、市、区
		/*
		 * if (provinceList != null && !provinceList.isEmpty()) {
		 * mCurrentProviceName = provinceList.get(0).getName(); List<CityModel>
		 * cityList = provinceList.get(0).getCityList(); if (cityList != null &&
		 * !cityList.isEmpty()) { mCurrentCityName = cityList.get(0).getName();
		 * List<AreaBean> districtList = cityList.get(0) .getDistrictList();
		 * mCurrentDistrictName = districtList.get(0).getRname(); //
		 * mCurrentZipCode = districtList.get(0).getZipcode(); } }
		 */
		// */
		/*
		 * mProvinceDatas = new String[provinceList.size()]; for (int i = 0; i <
		 * provinceList.size(); i++) { // 遍历所有省的数据 mProvinceDatas[i] =
		 * provinceList.get(i).getName(); List<CityModel> cityList =
		 * provinceList.get(i).getCityList(); String[] cityNames = new
		 * String[cityList.size()]; for (int j = 0; j < cityList.size(); j++) {
		 * // 遍历省下面的所有市的数据 cityNames[j] = cityList.get(j).getName();
		 * List<AreaBean> districtList = cityList.get(j) .getDistrictList();
		 * //String[] distrinctNameArray = new String[districtList // .size()];
		 * //AreaBean[] distrinctArray = new AreaBean[districtList // .size()];
		 * for (int k = 0; k < districtList.size(); k++) { // 遍历市下面所有区/县的数据
		 * AreaBean districtModel = new AreaBean(
		 * districtList.get(k).getRname(), districtList
		 * .get(k).getPid(),districtList.get(k).getRtype()); //
		 * 区/县对于的邮编，保存到mZipcodeDatasMap
		 * mZipcodeDatasMap.put(districtList.get(k).getName(),
		 * districtList.get(k).getZipcode()); distrinctArray[k] = districtModel;
		 * distrinctNameArray[k] = districtModel.getRname(); } //
		 * 市-区/县的数据，保存到mDistrictDatasMap mDistrictDatasMap.put(cityNames[j],
		 * distrinctNameArray); } // 省-市的数据，保存到mCitisDatasMap
		 * mCitisDatasMap.put(provinceList.get(i).getName(), cityNames); }
		 */

	}


}
