package com.swiperefreshlayout;

import android.content.Context;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 适配器
 */
public class MenuAdapter extends CommonRecyclerAdapter<String> {

    public MenuAdapter(Context context, int layoutResId,List<String> data) {
        super(context, layoutResId,data);
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, String item, int position) {
        helper.setText(R.id.menuitem_tv,item);
    }
}
