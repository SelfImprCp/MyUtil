package com.cp.mylibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 * 需要在Application中注册，为了要在程序启动器就监控整个程序。
 * <p>
 * <p>
 * 用法 ：Application ：
 *
 * @Override public void onCreate() {
 * super.onCreate();
 * <p>
 * // 异常处理，不需要处理时注释掉这两句即可！
 * CrashHandler crashHandler = CrashHandler.getInstance();
 * // 注册crashHandler
 * crashHandler.init(getApplicationContext());
 * <p>
 * <p>
 * }
 * <p>
 * <p>
 * Created by Jerry on 2016/7/5.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {


    /**
     * TAG
     */
    public static final String TAG = "CrashHandler";
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler mCrashHandler;
    /**
     * 程序的Context对象
     */
    private Context mContext;
    /**
     * 用来存储设备信息和异常信息
     */
    private Map<String, String> infos = new HashMap<String, String>();
    /**
     * 用于格式化日期,作为日志文件名的一部分
     */
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    /**
     * 日志保存位置
     */
    private String path = SDCardUtils.SDPATH + "crash/";


    /**
     * 私有构造函数
     */
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例 ,单例模式
     *
     * @return
     * @since V1.0
     */
    public static CrashHandler getInstance() {
        if (mCrashHandler == null)
            mCrashHandler = new CrashHandler();
        return mCrashHandler;
    }

    /**
     * 初始化
     *
     * @param context
     * @since V1.0
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LogCp.e(TAG, "uncaughtException() InterruptedException:" + e);
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     * @since V1.0
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 收集设备参数信息
        collectDeviceInfo(mContext);

        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        // 保存日志文件
        saveCatchInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     * @since V1.0
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogCp.e(TAG, "collectDeviceInfo() an error occured when collect package info NameNotFoundException:", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                LogCp.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                LogCp.e(TAG, "collectDeviceInfo() an error occured when collect crash info Exception:", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCatchInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {


                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                LogCp.i(TAG, " 生成的异常文件保存路径 " + path + fileName);


                FileOutputStream fos = new FileOutputStream(path + fileName);


                fos.write(sb.toString().getBytes());

                fos.close();

                // 发送给开发人员
                sendCrashLog2PM(path, fileName);
            }
            return path + fileName;
        } catch (Exception e) {
            LogCp.e(TAG, "saveCatchInfo2File() an error occured while writing file... Exception:", e);
        }
        return null;
    }

    /**
     * 将捕获的导致崩溃的错误信息发送给开发人员 目前只将log日志保存在sdcard 和输出到LogCat中，并未发送给后台。
     *
     * @param fileName
     * @since V1.0
     */
    private void sendCrashLog2PM(String path, String fileName) {

        if (!FileUtil.isFileExist(path, fileName)) {
            LogCp.e(TAG, "sendCrashLog2PM() 日志文件不存在");
            return;
        }

        String strCache = FileUtil.readSDFile(path, fileName);

        LogCp.e(TAG, " 读取到的异常，可以传到服务了" + strCache);


    }
}
