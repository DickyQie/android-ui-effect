Android之SwipeRefreshLayout下拉刷新组件
<h4>SwipeRefreshLayout概述</h4> 
<p>SwipeRefrshLayout是Google官方更新的一个Widget,可以实现下拉刷新的效果。该控件集成自ViewGroup在support-v4兼容包下，不过我们需要升级supportlibrary的版本到19.1以上。</p> 
<p>用户通过手势或者点击某个按钮实现内容视图的刷新，布局里加入SwipeRefreshLayout嵌套一个子视图如ListView、 RecyclerView等，触发刷新会通过OnRefreshListener的onRefresh方法回调，我们在这里执行页面数据的刷新，每次手势 的完成都会执行一次通知，根据滑动距离判断是否需要回调。setRefreshing（false）通过代码直接取消刷新，true则手动设置刷新调出刷 新视图。setEnabled（false）通过boolean控制是否禁用手势刷新 。</p> 
<p>基本使用的方法如下:</p> 
<ol> 
 <li>setOnRefreshListener(OnRefreshListener):添加下拉刷新监听器</li> 
 <li>setRefreshing(boolean):显示或者隐藏刷新进度条</li> 
 <li>isRefreshing():检查是否处于刷新状态</li> 
</ol> 
<p>&nbsp;</p> 
<p>使用非常简单，用一个简单案例来介绍SwipeRefreshLayout下拉刷新的功能。</p> 
<p>布局文件</p> 
<pre><code class="language-html">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v4.widget.SwipeRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/v7_refresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/back"&gt;

    &lt;android.support.v7.widget.RecyclerView
        android:id="@+id/v7_recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" /&gt;

&lt;/android.support.v4.widget.SwipeRefreshLayout&gt;</code></pre> 
<p>item.xml</p> 
<pre><code class="language-html">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;android.support.v7.widget.CardView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cardview"
    app:cardCornerRadius="5dp"
    app:cardBackgroundColor="@android:color/white"
    android:layout_margin="5dp"
    android:layout_height="60dp"
    android:layout_width="match_parent"&gt;

        &lt;TextView
        android:id="@+id/menuitem_tv"
        android:layout_gravity="center"
        android:text="@string/app_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

&lt;/android.support.v7.widget.CardView&gt;</code></pre> 
<p>Activity</p> 
<pre><code class="language-java">public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private List&lt;String&gt; list=null;
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
        list=new ArrayList&lt;&gt;();
        for(int i=0;i&lt;22;i++){
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
}</code></pre> 
<p>运行效果如图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" height="536" src="https://static.oschina.net/uploads/space/2017/0218/094907_qDwq_2945455.gif" width="330"></p> 
<span id="OSC_h2_2"></span>
