package com.ll.support.template;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * author: ll
 */
public class TemplateActivity extends AppCompatActivity {

	private ViewGroup mRootView;
	private AppBarLayout mAppBarLayout;
	private ProgressBar mProgressBar;
	private ViewGroup mContentLayout;
	
	
	protected final ViewGroup getRootView() {
		return mRootView;
	}
	
	protected final ViewGroup getAppBarLayout() {
		return mAppBarLayout;
	}
	
	protected final ViewGroup getContentLayout() {
		return mContentLayout;
	}
	
	/**
     * 左返回箭头点击事件,默认关闭当前Activity,子类可以重写此函数实现自己的逻辑
     */
    public void onHomeClick() {
        onBackPressed();
    }

    public boolean isToolbarProgressShown() {
        return mProgressBar != null && mProgressBar.getVisibility() == View.VISIBLE;
    }

    /**
     *
     * @param show 显示隐藏顶部的progressBar
     */
    @MainThread
    public final void setToolbarProgressVisibility(boolean show) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     *
     * @return contentLayout的id,当界面是由单个fragment构成的时候用来放置fragment
     */
    @IdRes
    protected final int getContentLayoutId() {
        return mContentLayout == null ? 0 : mContentLayout.getId();
    }

    /**
     * @param drawable 设置导航栏左侧图标
     */
    public final void setHomeIcon(Drawable drawable) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
	        actionBar.setHomeAsUpIndicator(drawable);
        }
    }

    /**
     * 隐藏actionbar
     */
    @MainThread
    protected final void setNoToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().hide();
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

	/**
	 * 设置当前界面保持屏幕常亮
	 */
	@MainThread
    protected final void keepScreenOn() {
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 设置全屏
     */
    @MainThread
    protected final void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
	
	/**
	 * 设置toolbar的阴影效果
	 * @param elevation
	 */
	protected final void setToolbarElevation(float elevation) {
    	if (mAppBarLayout != null) {
		    ViewCompat.setElevation(mAppBarLayout, elevation);
	    }
    }
    
    protected boolean useTemplateLayout() {
		return true;
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
        	toolbar.setNavigationOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
					onHomeClick();
		        }
	        });
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public final void setContentView(@LayoutRes int layoutResID) {
    	if (useTemplateLayout()) {
    		setTemplateContentView();
		    setContentViewWithTemplateLayout(View.inflate(this, layoutResID, null), null);
	    } else {
    		super.setContentView(layoutResID);
	    }
    }

    @Override
    public final void setContentView(View view) {
    	if (useTemplateLayout()) {
		    setTemplateContentView();
		    setContentView(view, null);
	    } else {
    	    super.setContentView(view);
	    }
    }

    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
    	if (useTemplateLayout()) {
		    setTemplateContentView();
    		setContentViewWithTemplateLayout(view, params);
	    } else {
    		super.setContentView(view, params);
	    }
    }

	/**
	 * 使用模板布局
	 */
	public final void setTemplateContentView() {
		super.setContentView(R.layout.activity_template);
		initTemplateView();
    }
	
	private void setContentViewWithTemplateLayout(View view, @Nullable ViewGroup.LayoutParams params) {
		if (params == null) {
			params = new FrameLayout.LayoutParams(-1, -1);
			((FrameLayout.LayoutParams) params).gravity = Gravity.CENTER;
		}
		mContentLayout.addView(view, params);
	}
	
	/**
	 * 初始化 templateView
	 */
	protected void initTemplateView() {
		mRootView = (ViewGroup) findViewById(R.id.templateRootView);
		mAppBarLayout = (AppBarLayout) findViewById(R.id.templateAppBarLayout);
		Toolbar toolbar = (Toolbar) findViewById(R.id.templateToolbar);
		mProgressBar = (ProgressBar) findViewById(R.id.templateToolbarProgress);
		mContentLayout = (ViewGroup) findViewById(R.id.templateContentView);
		this.setSupportActionBar(toolbar);
	}
    
}
