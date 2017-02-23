# Android之省市区三级联动 
  <p><span style="background-color:rgb(255, 255, 255); color:rgb(0, 0, 0)">最近项目要做一个电商APP，选择收货地址的三级联动滚动选择组件，</span> 控件用起来非常简单 ，<span style="background-color:rgb(255, 255, 255); color:rgb(0, 0, 0)">下面是它的运行效果：</span></p> 
<p><span style="background-color:rgb(255, 255, 255); color:rgb(0, 0, 0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" height="304" src="https://static.oschina.net/uploads/space/2017/0223/094929_e9eQ_2945455.gif" width="335"></span></p> 
<p><span style="background-color:rgb(255, 255, 255); color:rgb(0, 0, 0)">布局</span></p> 
<pre><code class="language-html">&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:su="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical"
    &gt;

    &lt;TextView
        android:id="@+id/tv_name"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:gravity="center"
        android:textColor="#FF0000"
        android:textSize="22sp"
        /&gt;
    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="选择地址"
        /&gt;
    &lt;com.city.linkage.CityPickerView
        android:id="@+id/citypicker"
        android:layout_width="fill_parent"
        android:layout_height="156dp"
        android:layout_gravity="center"
        android:gravity="center"&gt;&lt;/com.city.linkage.CityPickerView&gt;
    &lt;Button
        android:id="@+id/btn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="确定"
        /&gt;

&lt;/LinearLayout&gt;</code></pre> 
<p>MainActivity.Java</p> 
<pre><code class="language-java">public class MainActivity extends AppCompatActivity {

    private CityPickerView cityPicker;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityPicker = (CityPickerView) findViewById(R.id.citypicker);
        tv=(TextView)findViewById(R.id.tv_name);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(cityPicker.getCity_string());
            }
        });
    }
}</code></pre> 
<span id="OSC_h3_1"></span>
