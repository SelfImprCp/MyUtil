package cn.myasapp.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Administrator
 */
public class SharePreferencesUitl {


    private final String CONFIG_TAG = "config";
    private static Context mContext;
    private static SharePreferencesUitl sharePreferencesUitl;

    /**
     * 获取Preference设置
     */

    public static SharePreferencesUitl getSharePreferencesUitl(Context context) {
        if (sharePreferencesUitl == null) {
            sharePreferencesUitl = new SharePreferencesUitl();
            mContext = context;
        }
        return sharePreferencesUitl;
    }


    /**
     * 保存数据
     *
     * @param key
     * @param value
     */
    private void saveData(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_TAG,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);

        editor.commit();
    }

    /**
     * 取得数据
     *
     * @param key
     * @return
     */
    private String loadData(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(CONFIG_TAG,
                Context.MODE_PRIVATE);
        String value = sp.getString(key, "");
        return value;


    }


}
