package com.swiperefreshlayout;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.classic.adapter.CommonRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private List<String> list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
    }
    private void initView()
    {
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.v7_refresh);
        recyclerView=(RecyclerView)findViewById(R.id.v7_recyclerView);
        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        // 设定下拉圆圈的背景:默认white
        // swipeRefreshLayout.setProgressBackgroundColor(android.R.color.white);
        initData();
    }
    private void initData()
    {
        bindData();
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Toast.makeText (MainActivity.this,"正在刷新",Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText (MainActivity.this,"刷新完成",Toast.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });

    }

    private void bindData(){
        list=new ArrayList<>();
        for(int i=0;i<22;i++){
            list.add("Item"+(i+1));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MenuAdapter menuAdapter=new MenuAdapter(this,R.layout.item,list);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {

                Toast.makeText (MainActivity.this, list.get(position),Toast.LENGTH_LONG).show();

            }
        });
    }
}
