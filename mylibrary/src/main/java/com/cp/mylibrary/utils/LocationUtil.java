package com.cp.mylibrary.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 *  关于位置的工具类
 * @author Administrator
 *
 */


public class LocationUtil {

   /*
    * 判断GPS定位服务是否开启
    */
   public boolean hasLocationGPS(Context mContext) {
       LocationManager manager = (LocationManager) mContext
               .getSystemService(Context.LOCATION_SERVICE);
       if (manager
               .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           return true;
       } else {
           return false;
       }
   }


   /*
    * 判断基站定位是否开启
    */

   public boolean hasLocationNetWork(Context mContext) {
       LocationManager manager = (LocationManager) mContext
               .getSystemService(Context.LOCATION_SERVICE);
       if (manager
               .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
           return true;
       } else {
           return false;
       }
   }







}
