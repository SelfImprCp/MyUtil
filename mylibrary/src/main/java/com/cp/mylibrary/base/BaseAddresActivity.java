package com.cp.mylibrary.base;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.cp.mylibrary.bean.ProvinceBean;
import com.cp.mylibrary.res.AreaRes;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseAddresActivity extends MyBaseActivity {

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

    // 所有的省
    public ArrayList<AreaBean> provincesList;

    // 所有市
    public ArrayList<AreaBean> citysList;

    //  地区
    public OptionsPickerView pvOptions;


    public TimePickerView pvTime;


    //省
    public ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();

    public ArrayList<ArrayList<ProvinceBean>> options2Items = new ArrayList<ArrayList<ProvinceBean>>();


    protected void initProvinceDatas() {

        String strAreaJson = GsonUtil.getJsonForFile(mContext, "area_json.txt");

        AreaRes areaRes = GsonUtil.jsonStrToBean(strAreaJson, AreaRes.class);

        // 所有的省
        provincesList = areaRes.getResult().getProvinces();
        // 所有的市
        citysList = areaRes.getResult().getCitys();
        // 所有的区
        List<AreaBean> areasList = areaRes.getResult().getAreas();

        // 所有的省
        // List<AreaBean> newProvincesList = new ArrayList<AreaBean>();


        LogCp.i(LogCp.CP, BaseAddresActivity.class + " 多少省  ，，"
                + provincesList.size() + " ,,, 多少个市 ，， " + citysList.size()
                + " ,, 多少区，， "

                + areasList.size());

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
            int proID = StringUtils.toInt(province.getId());

            mProvinceDatas[i] = provincesList.get(i).getRname();
            // mProvinceDatas[province.getRname()];
            // 某个省下所有的市
            List<AreaBean> newCitysList = new ArrayList<AreaBean>();

            // 把市放到省里
            for (int j = 0; j < citysList.size(); j++) {

                AreaBean city = citysList.get(j);
                mCityDatas[j] = city.getRname();
                // 上级的id 和此类中的爸爸级ID相同，说明这个当前市是当前省下的


                if (proID == city.getPid()) {
                    newCitysList.add(city);

                    // 把区放到市里面


                    int cityID = StringUtils.toInt(city.getId());

                    // 某个市下所有的区
                    List<AreaBean> newAreasList = new ArrayList<AreaBean>();


                    for (AreaBean area : areasList) {

                        if (cityID == area.getPid()) {


                            newAreasList.add(area);

                        }

                    }

                    LogCp.i(LogCp.CP,
                            BaseAddresActivity.class + " 当前的市  " + city.getRname() + "当前市下有多少区：" + newAreasList.size());

                    mDistrictDatasMap.put(city.getRname(), newAreasList);
                }
            }


            LogCp.i(LogCp.CP,
                    BaseAddresActivity.class + " 当前的省  " + province.getRname() + "当前省下有多少市" + newCitysList.size());

            mCitisDatasMap.put(province.getRname(), newCitysList);

        }


    }

    /**
     *
     */

    public void showTime() {
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
       Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 2000, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        pvTime.show();


    }


    public void showSelectArea() {

        initProvinceDatas();


        //选项选择器
        pvOptions = new OptionsPickerView(this);


        for (AreaBean areaBean : provincesList) {

            ProvinceBean pro = new ProvinceBean(areaBean.getId(), areaBean.getRname());
            options1Items.add(pro);


            ArrayList<ProvinceBean> options2Items_01 = new ArrayList<ProvinceBean>();

            for (AreaBean cityBean : citysList) {


                LogCp.i(LogCp.CP, BaseAddresActivity.class +
                        "  当前省的id   " + areaBean.getId() +
                        " 当前市的" + cityBean.getRname() + cityBean.getPid()
                );


                if (areaBean.getId() ==cityBean.getPid()) {


                    ProvinceBean proTwo = new ProvinceBean(cityBean.getId(), cityBean.getRname());


                    options2Items_01.add(proTwo);


                }


            }
            options2Items.add(options2Items_01);


        }


        //三级联动效果

        //
        //  pvOptions.setPicker(options1Items);
        //
        pvOptions.setPicker(options1Items, options2Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
//		pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//
//			@Override
//			public void onOptionsSelect(int options1, int option2, int options3) {
//				//返回的分别是三个级别的选中位置
////                 String tx = options1Items.get(options1).getPickerViewText()
////                         + options2Items.get(options1).get(option2);
//
//				vMasker.setVisibility(View.GONE);
//			}
//		});


        pvOptions.show();


    }


}
