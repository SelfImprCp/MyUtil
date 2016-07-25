package com.cp.mylibrary.res;


import com.cp.mylibrary.base.AreaListBean;

/**
 *  地区的返回 类
 * @author Administrator
 *
 */

public class AreaRes extends Response {


    private AreaListBean result;

   public  AreaListBean  getResult() {
       return result;
   }

   public void setResult( AreaListBean   result) {
       this.result = result;
   }



}
