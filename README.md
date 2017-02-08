# Android之RecyclerView实现时光轴 
 <p><span style="background-color:rgb(255, 255, 255); color:rgb(47, 47, 47)">做项目的过程中有个需求需要时光轴，于是网上找了部分资料</span> ，写了个案例，现在分享给大家。</p> 
<p>如图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" height="525" src="https://static.oschina.net/uploads/space/2017/0208/145405_aGyV_2945455.png" width="296"></p> 
<p>activity_main.xml</p> 
<pre><code class="language-html">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"&gt;

    &lt;android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" /&gt;

&lt;/RelativeLayout&gt;</code></pre> 
<p>item.xml</p> 
<pre><code class="language-html">&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="8dp"
    android:paddingRight="8dp"
    android:paddingTop="8dp"&gt;
    &lt;TextView
        android:id="@+id/item_timeline_time"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:layout_marginRight="8dp"
        android:layout_marginTop="18dp"
        android:gravity="center_horizontal"
        android:padding="4dp"
        android:textColor="@color/colorAccent"
        android:textSize="16sp"
        android:text="2015-06-08\n09:56"
        /&gt;

    &lt;RelativeLayout
        android:id="@+id/item_timeline_icon_layout"
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:layout_marginBottom="4dp"
        android:layout_marginRight="8dp"
        android:layout_toRightOf="@id/item_timeline_time"&gt;

        &lt;com.timelinedemo.CircleImageView
            android:id="@+id/item_timeline_icon_bg"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:src="@android:color/transparent"
            app:civ_border_width="4dp" /&gt;

        &lt;ImageView
            android:id="@+id/item_timeline_icon"
            android:layout_width="24dp"
            android:layout_height="24dp"
            android:layout_centerInParent="true"
            android:scaleType="fitCenter" /&gt;

    &lt;/RelativeLayout&gt;

    &lt;TextView
        android:id="@+id/item_timeline_content"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="18dp"
        android:layout_toRightOf="@id/item_timeline_icon_layout"
        android:text="今日收入"
        android:textColor="@color/colorPrimary"
        android:textSize="15sp" /&gt;

    &lt;TextView
        android:id="@+id/item_timeline_money"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/item_timeline_content"
        android:layout_marginTop="8dp"
        android:layout_toRightOf="@id/item_timeline_icon_layout"
        android:text="$ 100"
        android:textColor="@color/colorPrimary"
        android:textSize="22sp" /&gt;
    &lt;View
        android:id="@+id/item_timeline_view"
        android:layout_width="2dp"
        android:layout_height="60dp"
        android:layout_alignLeft="@id/item_timeline_icon_layout"
        android:layout_below="@id/item_timeline_icon_layout"
        android:layout_marginLeft="23dp"
        android:background="@color/colorAccent" /&gt;
&lt;/RelativeLayout&gt;
</code></pre> 
<p>Activity.Java</p> 
<pre><code class="language-java">public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
    }
    private void initLayout(){
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
    }
    private void initData(){
        List&lt;TimeInfo &gt; list=new ArrayList&lt;&gt;();
        for(int i=0;i&lt;15;i++){
            list.add(new TimeInfo());
        }
        TimelineAdapter mAdapter = new TimelineAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
    }
}
</code></pre> 
<span id="OSC_h3_1"></span>
<h3>添加依赖库：</h3> 
<pre><code class="language-java">compile 'com.android.support:recyclerview-v7:23.0.0'</code></pre> 
