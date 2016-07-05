package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.utils.FileUtil;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import java.io.IOException;

import cn.myasapp.R;

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
                    String readContent = fileUtilT.readDataDataPackageFiles("test");
                    ShowToastUtil.showToast(this, "  读取到的文件内容  " + readContent);
                    //   LogCp.i(LogCp.CP, PlanFragment.class + "  读取到的文件内容  " + readContent );


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


            case R.id.file_isExist_test:
                boolean isExist = FileUtil.isFileExist(FileUtil.DATA_DATA_FILE_PATH, "test");
                ShowToastUtil.showToast(this, "文件是否存在：" + isExist);
                break;

            case R.id.file_delete_test:
                boolean isDelete = FileUtil.delelteFile(FileUtil.DATA_DATA_FILE_PATH, "test");
                ShowToastUtil.showToast(this, "文件是否删除：" + isDelete);

                break;

            //读取 sdcard 上的文件
            case R.id.file_read_sdcare_file_test:

                String string = FileUtil.readSDFile(FileUtil.SDPATH, "cp");

                //   LogCp.i(LogCp.CP, TestActivity.class + " 读到的内容" + string);
                ShowToastUtil.showToast(this, " 读到的内容" + string);


                break;
            // 写入 sd 文件
            case R.id.file_write_sdcare_file_test:

                boolean isSave = FileUtil.saveContentToSDCard("这 内容 是写到sd卡上的", FileUtil.SDPATH, "cp");
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
                String strSize = FileUtil.formatFileSize(FileUtil.getFileSize(FileUtil.SDPATH, "cp"));

                //    LogCp.i(LogCp.CP, TestActivity.class + "  算出来的文件 大小， " + strSize);
                ShowToastUtil.showToast(this, "  算出来的文件 大小， " + strSize);


                break;

            case R.id.file_get_iszai:


                boolean isSD = FileUtil.isFileExist(FileUtil.SDPATH, "cp");

                ShowToastUtil.showToast(this, "   SD 卡上的文件是否存在， " + isSD);

                //   LogCp.i(LogCp.CP, TestActivity.class + "   SD 卡上的文件是否存在， " + isSD);

                boolean isDATA = FileUtil.isFileExist(FileUtil.DATA_DATA_FILE_PATH, "test");

                //        LogCp.i(LogCp.CP, TestActivity.class + "   DATA  卡上的文件是否存在， " + isDATA);
                ShowToastUtil.showToast(this, "   DATA  卡上的文件是否存在， " + isDATA);

                break;


        }
    }
}
