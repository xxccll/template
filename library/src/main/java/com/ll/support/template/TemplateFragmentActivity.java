package com.ll.support.template;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * author: ll
 */

public class TemplateFragmentActivity extends TemplateActivity {

	public static final String TAG = TemplateFragmentActivity.class.getSimpleName();
	public static final String FROM_TAG = TemplateActivity.class.getSimpleName();

	/**
	 * 根据加载的 fragment 生成 intent
	 *
	 * @param context 上下文
	 * @param aClass  要加载的 fragment
	 * @return 封装好的 intent
	 */
	public static Intent getIntent(Context context, Class<? extends Fragment> aClass) {
		Intent intent = new Intent();
		intent.setClass(context, TemplateFragmentActivity.class);
		intent.putExtra(FROM_TAG, context.getClass().getName());
		intent.putExtra(TAG, aClass.getName());
		return intent;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putAll(getIntent().getExtras());
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTemplateContentView();
		Bundle arg = savedInstanceState;
		if (arg == null) {
			arg = getIntent().getExtras();
		} else {
			getIntent().putExtras(arg);
		}
		String fTag = arg.getString(TAG);
		if (savedInstanceState == null) {
			Fragment fragment = Fragment.instantiate(this, fTag, arg);
			getSupportFragmentManager().beginTransaction().replace(getContentLayoutId(), fragment, TAG).commitAllowingStateLoss();
		}
	}
	
	@Override
	public void onHomeClick() {
		onBackPressed();
	}
	
	@Override
	public void onBackPressed() {
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
		if (!(fragment instanceof TemplateFragment) || !((TemplateFragment)fragment).onBackPressed()) {
			super.onBackPressed();
		}
	}
	
}
