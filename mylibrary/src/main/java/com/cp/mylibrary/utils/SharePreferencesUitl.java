package com.cp.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author Administrator
 */
public class SharePreferencesUitl {

    private final static String APP_CONFIG = "config";

    private static Context mContext;
    private static SharePreferencesUitl sharePreferencesUitl;

    /**
     * 获取Preference设置
     */
    // public static SharedPreferences getSharedPreferences(Context context) {
    // mContext = context;
    // return PreferenceManager.getDefaultSharedPreferences(context);
    // }
    public static SharePreferencesUitl getSharePreferencesUitl(Context context) {
        if (sharePreferencesUitl == null) {
            sharePreferencesUitl = new SharePreferencesUitl();
            mContext = context;
        }
        return sharePreferencesUitl;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator
                    + APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void saveData(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String loadData(String key) {
        SharedPreferences sp = mContext.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        String str = sp.getString(key, "");
        return str;
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }


    public void remove(String... key) {
        Properties props = get();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }

    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        set(ps);
    }

    public Properties getProperties() {
        return get();
    }

    public void setProperty(String key, String value) {
        set(key, value);
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = get(key);
        return res;
    }

    public void removeProperty(String... key) {
        remove(key);
    }

}
