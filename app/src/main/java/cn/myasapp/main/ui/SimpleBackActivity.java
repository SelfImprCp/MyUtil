package cn.myasapp.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.cp.mylibrary.base.MyBaseFragment;
import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.lang.ref.WeakReference;

import cn.myasapp.main.R;
import cn.myasapp.main.domian.SimpleBackPage;


/**
 * 应用二级界面
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 * 
 */
public class SimpleBackActivity extends BaseActivity {
	public static String TAG = SimpleBackActivity.class.getSimpleName();
	public static String CONTENT_KEY = "sba_content_key";
	public static String DATA_KEY = "sba_datat_key";
	public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
	public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
	private MyBaseFragment currentFragment;
	public static WeakReference<Fragment> mFragment;
	protected int mPageValue = -1;

	public static SimpleBackActivity activity = new SimpleBackActivity();



	@BindView(id = R.id.aty_simpleback_title)
	 public TitleBarView aty_simpleback_title;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int value = getIntent().getIntExtra(CONTENT_KEY, -1);
		if (value != -1) {
			try {
				currentFragment = (MyBaseFragment) SimpleBackPage
						.getPageByValueClass(value).newInstance();
				changeFragment(currentFragment);
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
		}

		if (mPageValue == -1) {
			mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
		}
		initFromIntent(mPageValue, getIntent());


	}

	@Override
	public void setRootView() {
		setContentView(R.layout.aty_simple_back);

	}




	/**
	 * 初始化控件
	 */
	public void initView() {
		aty_simpleback_title.setTitleBackFinshActivity(this);
		aty_simpleback_title.setTitleStr("测试 ");


	}

	@Override
	public void onBackPressed() {
		if (mFragment != null && mFragment.get() != null
				&& mFragment.get() instanceof MyBaseFragment) {
			MyBaseFragment bf = (MyBaseFragment) mFragment.get();

		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	protected void initFromIntent(int pageValue, Intent data) {
		if (data == null) {
			throw new RuntimeException(
					"you must provide a page info to display");
		}
		SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);


		LogCp.i(LogCp.CP,SimpleBackActivity.class + " SimpleBackPage " + page);

		if (page == null) {
			throw new IllegalArgumentException("can not find page by value:"
					+ pageValue);
		}


		try {
			Fragment fragment = (Fragment) page.getClazz().newInstance();

			Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
			if (args != null) {
				fragment.setArguments(args);
			}



			FragmentTransaction getFragmentManager = getSupportFragmentManager().beginTransaction();



			getFragmentManager.replace(R.id.main_content, fragment, TAG);
			getFragmentManager.addToBackStack(null);
			getFragmentManager.commitAllowingStateLoss();


		 mFragment = new WeakReference<Fragment>(fragment);

		} catch (Exception e) {
			e.printStackTrace();
			// throw new IllegalArgumentException(
			 //		"generate fragment error. by value:" + pageValue);
		}
	}




	public Bundle getBundleData() {
		return getIntent().getBundleExtra(DATA_KEY);
	}

	public void changeFragment(SupportFragment targetFragment) {
		// super.changeFragment(R.id.main_content, targetFragment);
		// changeFragment(R.id.main_content,currentFragment);
		super.changeFragment(R.id.main_content, targetFragment);
	}


}
