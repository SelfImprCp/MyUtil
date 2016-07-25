package com.cp.mylibrary.base;

import com.cp.mylibrary.bean.MyEntity;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 *
 */
public class AreaListBean extends MyEntity{
   private ArrayList<AreaBean> provinces;
   private ArrayList<AreaBean> citys;
   private List<AreaBean> areas;
   public ArrayList<AreaBean> getProvinces() {
       return provinces;
   }
   public void setProvinces(ArrayList<AreaBean> provinces) {
       this.provinces = provinces;
   }
   public ArrayList<AreaBean> getCitys() {
       return citys;
   }
   public void setCitys(ArrayList<AreaBean> citys) {
       this.citys = citys;
   }
   public List<AreaBean> getAreas() {
       return areas;
   }
   public void setAreas(List<AreaBean> areas) {
       this.areas = areas;
   }



}
