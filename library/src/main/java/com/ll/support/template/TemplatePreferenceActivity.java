package com.ll.support.template;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

/**
 * author: ll
 */
@SuppressWarnings("Deprecated")
public class TemplatePreferenceActivity extends PreferenceActivity {
    private Toolbar _toolbar;

    protected final Toolbar getToolBar() {
        return _toolbar;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        _toolbar.setTitle(title);
    }

    private void setToolBar(){
        setContentView(R.layout.activity_template_preference);
        _toolbar = (Toolbar) findViewById(R.id.abp__toolbar);
        _toolbar.setClickable(true);
        _toolbar.setNavigationIcon(getResIdFromAttribute(this, R.attr.homeAsUpIndicator));
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onBackPressed();
            }
        });
    }

    private static int getResIdFromAttribute(final Activity activity, final int attr) {
        if (attr == 0) {
            return 0;
        }
        final TypedValue typedValueAttr = new TypedValue();
        activity.getTheme().resolveAttribute(attr, typedValueAttr, true);
        return typedValueAttr.resourceId;
    }

}