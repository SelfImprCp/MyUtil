package com.cp.mylibrary.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.database.DaoConfig;
import org.kymjs.kjframe.database.utils.Property;
import org.kymjs.kjframe.database.utils.TableInfo;
import org.kymjs.kjframe.utils.KJConfig;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyDbUtil {


    public KJDB getKJDB(Context context,int version ,String dbName) {

        DaoConfig daoConfig = new DaoConfig();

        daoConfig.setDbVersion(version);
        daoConfig.setDbName(dbName);
        daoConfig.setContext(context);

        KJDB kjdb = KJDB.create(daoConfig);



        return kjdb;

    }


//    /**
//     *
//     * @param context
//     */
//
//    public  void   dropDb(Context context ) {
//        getKJDB(context).dropDb();
//
//    }
//
//
//    /**
//     *
//     * @param context
//     * @param version
//     */
//    public  void   setDbVersion(Context context,int version ) {
//
//        KJDB kjdb =   getKJDB(context);
//
//
//
//
//    }




//    /**检查类变化时数据库表结构是否变化并修改*/
//    public void checkDb(KJDB db) {
//


//
//        /***/
//        Cursor cursor = db.rawQuery(
//                "SELECT name FROM sqlite_master WHERE type ='table'", null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                try {
//                    String tablename = cursor.getString(0);
//                    String classname = tablename.replaceAll("_", ".");
//                    /**查询表所映射类的信息*/
//                    TableInfo info = TableInfo.get(classname);
//                    if (info != null) {
//                        Iterator<Map.Entry<String, Property>> it = info.propertyMap
//                                .entrySet().iterator();
//                        // 检查该表是否有这个字段
//                        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
//                        try {
//                            Cursor columns = db.rawQuery("SELECT * FROM "
//                                    + tablename + " LIMIT 0", null);
//                            for (String name : columns.getColumnNames()) {
//                                map.put(name, false);
//                            }
//
//                        } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                            throw new RuntimeException(e);
//                        }
//                        /** 遍历类所有字段 */
//                        while (it.hasNext()) {
//                            Map.Entry<String, Property> item = it.next();
//                            String key = item.getKey();
//                            /**该字段不存在新建*/
//                            if (map.get(key) == null) {
//                                db.execSQL("ALTER TABLE " + tablename
//                                        + " ADD COLUMN " + key + " " + "CHAR");
//                                map.put(key, true);
//                            }
//                        }
//                        it = info.propertyMap
//                                .entrySet().iterator();
//                        /**删除未映射字段*/
//                        while(it.hasNext()){
//                            Map.Entry<String, Property> item = it.next();
//                            String key = item.getKey();
//                            /**该字段未被遍历删除*/
//                            if (map.get(key)==true) {
//                                db.execSQL("ALTER TABLE "+tablename+" DROP COLUMN "+key);
//                            }
//                        }
//                    }
//
//                } catch (SQLException e) {
//                    KJLoger.debug(getClass().getName() + e.getMessage());
//                }
//            }
//        }
//        if (cursor != null) {
//            cursor.close();
//            cursor = null;
//        }
//
//    }





}
