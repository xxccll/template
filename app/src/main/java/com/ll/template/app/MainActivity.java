package com.ll.template.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.ll.support.template.TemplateActivity;

public class MainActivity extends TemplateActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setTemplateContentView();
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setHomeButtonEnabled(false);
			actionBar.setIcon(R.mipmap.ic_launcher);
		}
	}

}
