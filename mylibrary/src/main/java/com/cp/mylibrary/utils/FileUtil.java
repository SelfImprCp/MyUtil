package com.cp.mylibrary.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件工具类
 *
 * @author zs 2015-8-8
 */
public class FileUtil {


//    一、 files
//    1. Context.getFilesDir()，该方法返回/data/data/youPackageName/files的File对象。
//    2. Context.openFileInput()与Context.openFileOutput()，只能读取和写入files下的文件，返回的是FileInputStream和FileOutputStream对象。www.2cto.com
//    3. Context.fileList()，返回files下所有的文件名，返回的是String[]对象。
//            4. Context.deleteFile(String)，删除files下指定名称的文件。
//
//    二、cache
//    1. Context.getCacheDir()，该方法返回/data/data/youPackageName/cache的File对象。
//
//    三、custom dir
//    getDir(String name, int mode)，返回/data/data/youPackageName/下的指定名称的文件夹File对象，如果该文件夹不存在则用指定名称创建一个新的文件夹。
//
//    一些路径的标准写法
//    Environment.getDataDirectory() = /data
//    Environment.getDownloadCacheDirectory() = /cache
//    Environment.getExternalStorageDirectory() = /mnt/sdcard
//    Environment.getRootDirectory() = /system
//    context.getCacheDir() = /data/data/com.mt.mtpp/cache
//


    private Context context;



//    public static String DATA_DATA_FILE_PATH = "/data/data/cn.myasapp.main/files/";
//
//    public static String DATA_DATA_CACHE_PATH = "/data/data/cn.myasapp.main/cache/";

   public static String getDataDataFilePath(Context context)
   {
        return "/data/data/" + AppUtils.getPackageName(context) + "/files/";

   }

    public static String getDataDataCachePath(Context context)
    {
        return "/data/data/" + AppUtils.getPackageName(context) + "/cache/";

    }


    public FileUtil(Context contex) {
        this.context = contex;
    }

    /**
     * 以private方式保存文件到手机自带存储空间 只能被本应用访问 写入的内容会覆盖原文件的内容
     * 路径: data\data\包名\files\文件名
     *
     * @param name    文件名
     * @param content 文件内容
     */
    public void savePrivateDataDataPackageFiles(String name, String content) throws Exception {
        saveCommon(name, content, Context.MODE_PRIVATE);
    }

    /**
     * 以append方式保存文件到手机自带存储空间 只能被本应用访问 会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
     * 路径: data\data\包名\files\文件名
     *
     * @param name    文件名
     * @param content 文件内容
     */
    public void saveAppendDataDataPackageFiles(String name, String content) throws Exception {
        saveCommon(name, content, Context.MODE_APPEND);
    }

    /**
     * 路径: data\data\包名\files\文件名
     * 以 读写(WR) 方式保存文件到手机自带存储空间
     *
     * @param name    文件名
     * @param content 文件内容
     */
    public void saveWRDataDataPackageFiles(String name, String content) throws Exception {
        saveCommon(name, content, Context.MODE_WORLD_WRITEABLE
                + Context.MODE_WORLD_READABLE);
    }

    /**
     * 公共的保存方法 文件会写到 data\data\包名\files\文件名
     *
     * @param name    文件名
     * @param content 文件内容
     * @param mode    文件操作模式
     *                <p>
     *                <p>
     *                私有模式
     *                ①只能被创建这个文件的当前应用访问
     *                ②若文件不存在会创建文件；若创建的文件已存在则会覆盖掉原来的文件
     *                Context.MODE_PRIVATE = 0;
     *                <p>
     *                追加模式
     *                ①私有的
     *                ②若文件不存在会创建文件；若文件存在则在文件的末尾进行追加内容
     *                Context.MODE_APPEND = 32768;
     *                <p>
     *                可读模式
     *                ①创建出来的文件可以被其他应用所读取
     *                Context.MODE_WORLD_READABLE=1;
     *                <p>
     *                可写模式
     *                ①允许其他应用对其进行写入。
     *                Context.MODE_WORLD_WRITEABLE=2
     *                <p>
     *                以上文件操作模式均针对保存在手机自带存储空间的文件。若文件存储在SDCard上，则不受读写控制。
     */
    public void saveCommon(String name, String content, int mode)
            throws Exception {
        // 得到文件输出流 (输入还是输出, 是相对于我们应用而言)
        // 文件名不能包含任何路径和分隔符 (/ \)
        FileOutputStream out = context.openFileOutput(name, mode);
        // 将文件内容写出去
        out.write(content.getBytes());
        out.close(); // 关闭流
    }


    /**
     * 读取文件内容 读取路径: data\data\包名\files\文件名
     *
     * @param name 文件名
     * @return 文件内容
     */
    public String readDataDataPackageFiles(String name) throws Exception {
        File file = new File(context.getFilesDir(), name);
        // FileInputStream in = context.openFileInput(name);
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1; // 存储读取的字节数
        // 输入流会把读取的内容放进byte[] buffer中
        while ((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len); // 把读取到的数据写到内存中去
        }
        byte[] data = baos.toByteArray(); // 拿到内存中的数据
        baos.close();
        in.close();
        return new String(data);
    }


    /**
     * 删除一个文件，
     *
     * @param name 文件名
     * @return 文件内容
     */
    public static boolean delelteFile(String path, String name) {
        boolean isSuccess = false;
        try {


            File file = new File(path, name);
            //

            if (file.exists()) {
                  isSuccess = file.delete();
                LogCp.i(LogCp.CP, FileUtil.class + "   删除 文件  文件路经：  " + path + name + " 是否删除成功：" + isSuccess);

            } else {
                LogCp.i(LogCp.CP, FileUtil.class + "   删除 文件  文件路经：  " + path + name + "  要删除的文件不存在：");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

         return  isSuccess;

    }


    /**
     * 判断文件是否存在
     *
     * @param path
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String path, String fileName) {
        return fileIsExists(path + fileName);
    }


    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            LogCp.i(LogCp.CP, FileUtil.class + "  判断文件是否存在，  文件路经：  " + path + " 是否存在：" + f.exists());


            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * SD卡下
     * 仓建一个文件夹
     * 新建目录
     *
     * @param directoryName
     * @return
     */
    public static boolean createDirectory(String directoryName) throws IOException {
        boolean status;
        if (!directoryName.equals("")) {
              File newPath = new File(SDCardUtils.SDPATH + directoryName);
            status = newPath.mkdirs();
            //status = true;
        } else
            status = false;
        return status;
    }



    /**
     * 删除目录(包括：目录里的所有文件)
     *
     * @param fileName
     * @return
     */
    public static boolean deleteDirectory(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {


            File newPath = new File(SDCardUtils.SDPATH + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isDirectory()) {
                String[] listfile = newPath.list();
                try {
                    for (int i = 0; i < listfile.length; i++) {
                        File deletedFile = new File(newPath.toString() + "/"
                                + listfile[i].toString());
                        deletedFile.delete();
                    }
                    newPath.delete();
                    //	Log.i("DirectoryManager deleteDirectory", fileName);
                    status = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = false;
                }

            } else
                status = false;
        } else
            status = false;
        return status;
    }




    /**
     *  写内容到sdCard
     * @param content
     * @param pathName
     * @param fileName
     */
    public static boolean saveContentToSDCard(String content, String pathName, String fileName) {

         boolean isSaveSuccess = false;
        try {
            File path = new File(pathName);
            File file = new File(pathName +"/"+ fileName);
            if (!path.exists()) {

              //  mkdir()：只能创建一层目录.
                boolean isSuccess =  path.mkdirs();
                LogCp.i(LogCp.CP, FileUtil.class + "  Create the file dir  " + pathName + " is success  " + isSuccess);

            }
            if (!file.exists()) {

                boolean isSuccessS =  file.createNewFile();
                LogCp.i(LogCp.CP, FileUtil.class + "  Create the file  " + fileName + " is success " + isSuccessS);
           }
            FileOutputStream
                    stream = new FileOutputStream(file);

            byte[] buf = content.getBytes();
            stream.write(buf);
            stream.close();
            isSaveSuccess = true;

        } catch (Exception e) {
            isSaveSuccess = false;
            LogCp.e(LogCp.CP, FileUtil.class + "  Error on writeFilToSD " + e.getMessage());
            e.printStackTrace();
        }
        return  isSaveSuccess;
    }

    /**

     * 读取SD卡中文本文件

     *

     * @param fileName

     * @return

     */

    public static String readSDFile(String pathName,String fileName) {

        StringBuffer sb = new StringBuffer();

        File file = new File(pathName + fileName);

        try {

            FileInputStream fis = new FileInputStream(file);

            int c;

            while ((c = fis.read()) != -1) {

                sb.append((char) c);

            }

            fis.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return sb.toString();

    }


    /**
     *
     * @param inStream
     * @return
     */

    public static String readInStream(InputStream inStream) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }

            outStream.close();
            inStream.close();
            return outStream.toString();
        } catch (IOException e) {
            Log.i("FileTest", e.getMessage());
        }
        return null;
    }



    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 根据文件的绝对路径获取文件名但不包含扩展名
     *
     * @param filePath
     * @return
     */
    public static String getFileNameNoFormat(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        int point = filePath.lastIndexOf('.');
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
                point);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (StringUtils.isEmpty(fileName))
            return "";

        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * 获取文件大小
     *
     * @param
     * @return
     */
    public static long getFileSize(String path,String fileName) {
        long size = 0;

        File file = new File(path +fileName);
        if (file != null && file.exists()) {
            size = file.length();
        }
        return size;
    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 获取目录文件个数
     *
     * @param
     * @return
     */
    public long getFileList(File dir) {
        long count = 0;
        File[] files = dir.listFiles();
        count = files.length;
        for (File file : files) {
            if (file.isDirectory()) {
                count = count + getFileList(file);// 递归
                count--;
            }
        }
        return count;
    }

    public static byte[] toBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            out.write(ch);
        }
        byte buffer[] = out.toByteArray();
        out.close();
        return buffer;
    }

    /**
     * 检查文件是否存在
     *
     * @param name
     * @return
     */
    public static boolean checkFileExists(String path ,String name) {
        boolean status;
        if (!name.equals("")) {
             File newPath = new File(path + name);
            status = newPath.exists();
        } else {
            status = false;
        }
        return status;
    }

    /**
     * 检查路径是否存在
     *
     * @param path
     * @return
     */
    public static boolean checkFilePathExists(String path) {
        return new File(path).exists();
    }






    /**
     * 删除文件
     *
     * @param
     * @return
     */
    public static boolean deleteFile(String path,String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {


            File newPath = new File(path + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile()) {
                try {

                    LogCp.i(LogCp.CP, FileUtil.class + "DirectoryManager deleteFile" + fileName);

                    newPath.delete();
                    status = true;
                } catch (SecurityException se) {
                    se.printStackTrace();
                    status = false;
                }
            } else
                status = false;
        } else
            status = false;
        return status;
    }

    /**
     * 删除空目录
     * <p>
     * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
     *
     * @return
     */
    public static int deleteBlankPath(String path) {
        File f = new File(path);
        if (!f.canWrite()) {
            return 1;
        }
        if (f.list() != null && f.list().length > 0) {
            return 2;
        }
        if (f.delete()) {
            return 0;
        }
        return 3;
    }

    /**
     * 重命名
     *
     * @param oldName
     * @param newName
     * @return
     */
    public static boolean reNamePath(String oldName, String newName) {
        File f = new File(oldName);
        return f.renameTo(new File(newName));
    }


    /**
     * 清空一个文件夹
     *
     * @param
     */
    public static void clearFileWithPath(String filePath) {
        List<File> files = FileUtil.listPathFiles(filePath);
        if (files.isEmpty()) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                clearFileWithPath(f.getAbsolutePath());
            } else {
                f.delete();
            }
        }
    }

    /**
     * 获取SD卡的根目录
     *
     * @return
     */
    public static String getSDRoot() {

        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取手机外置SD卡的根目录
     *
     * @return
     */
    public static String getExternalSDRoot() {

        Map<String, String> evn = System.getenv();

        return evn.get("SECONDARY_STORAGE");
    }

    /**
     * 列出root目录下所有子目录
     *
     * @param
     * @return 绝对路径
     */
    public static List<String> listPath(String root) {
        List<String> allDir = new ArrayList<String>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        // 过滤掉以.开始的文件夹
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                if (f.isDirectory() && !f.getName().startsWith("build/intermediates/exploded-aar/com.android.support/support-vector-drawable/23.4.0/res")) {
                    allDir.add(f.getAbsolutePath());
                }
            }
        }
        return allDir;
    }

    /**
     * 获取一个文件夹下的所有文件
     *
     * @param root
     * @return
     */
    public static List<File> listPathFiles(String root) {
        List<File> allDir = new ArrayList<File>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        File[] files = path.listFiles();
        for (File f : files) {
            if (f.isFile())
                allDir.add(f);
            else
                listPath(f.getAbsolutePath());
        }
        return allDir;
    }



    /**
     * 截取路径名
     *
     * @return
     */
    public static String getPathName(String absolutePath) {
        int start = absolutePath.lastIndexOf(File.separator) + 1;
        int end = absolutePath.length();
        return absolutePath.substring(start, end);
    }

    /**
     * 获取应用程序缓存文件夹下的指定目录
     *
     * @param context
     * @param dir
     * @return
     */
    public static String getAppCache(Context context, String dir) {
        String savePath = context.getCacheDir().getAbsolutePath() + "/" + dir + "/";
        File savedir = new File(savePath);
        if (!savedir.exists()) {
            savedir.mkdirs();
        }
        savedir = null;
        return savePath;
    }



    /**
     * 读取assets 下的文件
     *
     * @param context
     * @param file
     * @return
     */

    public static String readAssetsFiel(Context context, String file) {
        String text;

        try {
            // Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(file);
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer, "UTF-8");

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

        return text;
    }


}
