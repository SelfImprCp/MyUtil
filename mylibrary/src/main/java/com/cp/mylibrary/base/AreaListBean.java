package com.cp.mylibrary.base;

import com.cp.mylibrary.bean.MyEntity;

import java.util.List;


/**
 *
 * @author Administrator
 *
 */
public class AreaListBean extends MyEntity{
   private List<AreaBean> provinces;
   private List<AreaBean> citys;
   private List<AreaBean> areas;
   public List<AreaBean> getProvinces() {
       return provinces;
   }
   public void setProvinces(List<AreaBean> provinces) {
       this.provinces = provinces;
   }
   public List<AreaBean> getCitys() {
       return citys;
   }
   public void setCitys(List<AreaBean> citys) {
       this.citys = citys;
   }
   public List<AreaBean> getAreas() {
       return areas;
   }
   public void setAreas(List<AreaBean> areas) {
       this.areas = areas;
   }



}
