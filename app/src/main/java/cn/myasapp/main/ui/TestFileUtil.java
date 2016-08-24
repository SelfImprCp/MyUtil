package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.FileUtil;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.SDCardUtils;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import java.io.IOException;

import cn.myasapp.main.R;
import cn.myasapp.main.event.TestEvent;


/**
 * Created by Jerry on 2016/7/4.
 * <p/>
 * <p/>
 * 测试文件工具类
 */
public class TestFileUtil extends BaseActivity {


    @BindView(id = R.id.file_test_title)
    private TitleBarView file_test_title;


    @BindView(id = R.id.file_read_test, click = true)
    private TextView file_read_test;


    @BindView(id = R.id.file_isExist_test, click = true)
    private TextView file_isExist_test;


    @BindView(id = R.id.file_delete_test, click = true)
    private TextView file_delete_test;


    @BindView(id = R.id.file_write_sdcare_file_test, click = true)
    private TextView file_write_sdcare_file_test;


    @BindView(id = R.id.file_read_sdcare_file_test, click = true)
    private TextView file_read_sdcare_file_test;


    @BindView(id = R.id.file_del_sdcare_dir_test, click = true)
    private TextView file_del_sdcare_dir_test;


    @BindView(id = R.id.file_sdcare_dir_test, click = true)
    private TextView file_sdcare_dir_test;

    @BindView(id = R.id.file_get_size, click = true)
    private TextView file_get_size;

    @BindView(id = R.id.file_get_iszai, click = true)
    private TextView file_get_iszai;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_file_test);
    }

    @Override
    protected void initView() {
        super.initView();

        file_test_title.setTitleBackFinshActivity(this);
        file_test_title.setTitleStr("文件 测试");


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {


            // 测试 写 文件
            case R.id.file_test:

                FileUtil fileUtil = new FileUtil(this);
                try {
                    fileUtil.savePrivateDataDataPackageFiles("test", "测试文件的写入，");
                } catch (Exception e) {
                    e.printStackTrace();
                }




                break;
            // 测试 读 文件

            case R.id.file_read_test:

                FileUtil fileUtilT = new FileUtil(this);

                try {


                    ///data/data/cn.myasapp.main/files/crash/crash-2016-07-05-16-29-12-1467707352667.log
                    String readContent = fileUtilT.readDataDataPackageFiles("test");
                    ShowToastUtil.showToast(this, "  读取到的文件内容  " + readContent);
                    //   LogCp.i(LogCp.CP, PlanFragment.class + "  读取到的文件内容  " + readContent );


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


            case R.id.file_isExist_test:

                 LogCp.i(LogCp.CP, TestActivity.class + "  取到的路径名 " + FileUtil.getDataDataFilePath(this));
                boolean isExist = FileUtil.isFileExist(FileUtil.getDataDataFilePath(this), "test");
                ShowToastUtil.showToast(this, "文件是否存在：" + isExist);
                break;

            case R.id.file_delete_test:
                boolean isDelete = FileUtil.delelteFile(FileUtil.getDataDataFilePath(this), "test");
                ShowToastUtil.showToast(this, "文件是否删除：" + isDelete);

                break;

            //读取 sdcard 上的文件
            case R.id.file_read_sdcare_file_test:


             //   /mnt/sdcard/MyUtil/crash/crash-2016-07-05-16-42-40-1467708160741.log

                String string = FileUtil.readSDFile(SDCardUtils.SDPATH+"crash/", "crash-2016-07-05-16-42-40-1467708160741.log");

                //   LogCp.i(LogCp.CP, TestActivity.class + " 读到的内容" + string);
                ShowToastUtil.showToast(this, " 读到的内容" + string);


                break;
            // 写入 sd 文件
            case R.id.file_write_sdcare_file_test:

                boolean isSave = FileUtil.saveContentToSDCard("这 内容 是写到sd卡上的", SDCardUtils.SDPATH, "cp");
                ShowToastUtil.showToast(this, " 是否保存成功 " + isSave);

                break;


            //删除sd目录
            case R.id.file_del_sdcare_dir_test:
                boolean deleteDirectory = FileUtil.deleteDirectory("jl");
                ShowToastUtil.showToast(this, " 目录 是否删除成功 " + deleteDirectory);

                break;

            // 创建sd目录
            case R.id.file_sdcare_dir_test:
                try {
                    boolean isSuccess = FileUtil.createDirectory("jl");
                    ShowToastUtil.showToast(this, " 目录 是否创建成功 " + isSuccess);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.file_get_size:
                String strSize = FileUtil.formatFileSize(FileUtil.getFileSize(SDCardUtils.SDPATH, "cp"));

                //    LogCp.i(LogCp.CP, TestActivity.class + "  算出来的文件 大小， " + strSize);
                ShowToastUtil.showToast(this, "  算出来的文件 大小， " + strSize);


                break;

            case R.id.file_get_iszai:


                boolean isSD = FileUtil.isFileExist(SDCardUtils.SDPATH, "cp");

                ShowToastUtil.showToast(this, "   SD 卡上的文件是否存在， " + isSD);

                //   LogCp.i(LogCp.CP, TestActivity.class + "   SD 卡上的文件是否存在， " + isSD);

                boolean isDATA = FileUtil.isFileExist(FileUtil.getDataDataFilePath(this), "test");

                //        LogCp.i(LogCp.CP, TestActivity.class + "   DATA  卡上的文件是否存在， " + isDATA);
                ShowToastUtil.showToast(this, "   DATA  卡上的文件是否存在， " + isDATA);



                // 测试 发个事件
                TestEvent event = new TestEvent();
                event.setString("测试 事件 ");
//                EventBus.getDefault().post(event);

                LogCp.i(LogCp.CP,TestFileUtil.class + "发出事件 了");




                break;


        }
    }
}
