# Android之自定义控件实现天气温度折线图和饼状图
 <p>以前写了个天气的APP，最近把他更新了一个版本，就抽取其中的<strong><span style="color:#ff0000">天气温度折现图</span></strong>这个功能写了这篇博客，来与大家分享，希望对你有所帮助。</p> 
<p>效果如图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" height="416" src="https://static.oschina.net/uploads/space/2017/0228/213708_p2Gp_2945455.gif" width="364"></p> 
<p>代码：</p> 
<p>MainActivity.Java</p> 
<pre><code class="language-java">/****
 * 饼状图和天气折线图
 */
public class MainActivity extends AppCompatActivity {

    private WeatnerChartView chart1;
    private WeatnerChartView chart2;
    private PinChart pinChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView()
    {
        chart1= (WeatnerChartView) findViewById(R.id.weather_char1);
        chart2= (WeatnerChartView) findViewById(R.id.weather_char2);
        pinChart= (PinChart) findViewById(R.id.pinchart);
        initData();
    }
    private void initData()
    {
        ArrayList&lt;WeatherItem&gt; list= new ArrayList&lt;WeatherItem&gt;();
        list.add(new WeatherItem("",18));
        list.add(new WeatherItem("",40));
        list.add(new WeatherItem("", -1));
        list.add(new WeatherItem("",1));
        list.add(new WeatherItem("",6));
        list.add(new WeatherItem("",2));
        list.add(new WeatherItem("", 33));
        chart1.SetTuView(list, "最高温度：");//单位: 摄氏度
        chart1.invalidate();
        ArrayList&lt;WeatherItem&gt; list1= new ArrayList&lt;WeatherItem&gt;();
        list1.add(new WeatherItem("",1));
        list1.add(new WeatherItem("",15));
        list1.add(new WeatherItem("", -6));
        list1.add(new WeatherItem("",-2));
        list1.add(new WeatherItem("", 3));
        list1.add(new WeatherItem("",-1));
        list1.add(new WeatherItem("",11));
        chart2.SetTuView(list1, "最低温度：");
        chart2.invalidate();
    }

}</code></pre> 
<p>饼状图自定义控件&nbsp;PinChart.java</p> 
<pre><code class="language-java">@SuppressLint("DrawAllocation") public class PinChart extends View {

	static Canvas c;
	private Paint[] mPaints;
	private Paint mTextPaint;
	private RectF mBigOval;
	public float[] mSweep = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static final float SWEEP_INC = 1;

	public static float[] humidity = { 110, 40, 50, 60, 50, 50 };

	public PinChart(Context context) {
		super(context);

	}

	public PinChart(Context context, AttributeSet atr) {
		super(context, atr);
	}

	@SuppressLint("DrawAllocation") @Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);// 设置背景颜色(透明)

		mPaints = new Paint[humidity.length];
		for (int i = 0; i &lt; humidity.length; i++) {
			mPaints[i] = new Paint();
			mPaints[i].setAntiAlias(true);
			mPaints[i].setStyle(Paint.Style.FILL);
			mPaints[i].setColor(0x880FF000 + i * 0x019e8860);
		}

		mBigOval = new RectF(40, 30, 220, 200);// 饼图的四周边界--&gt;左，上，右，下

		mTextPaint = new Paint();// 绘制文本
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(16F);// 设置温度值的字体大小
		float start = 0;
		for (int i = 0; i &lt; humidity.length; i++) {
			canvas.drawArc(mBigOval, start, mSweep[i], true, mPaints[i]);
			start += humidity[i];
			if (mSweep[i] &lt; humidity[i]) {
				mSweep[i] += SWEEP_INC;
			}
			canvas.drawRect(new RectF(300, 30 + 15 * i, 312, 42 + 15 * i),
					mPaints[i]);
			canvas.drawText("测试模块" + i, 320, 40 + 15 * i, mTextPaint);
		}
		invalidate();
	}

}</code></pre> 
<p>自定义控件&nbsp;WeatnerChartView.java</p> 
<pre><code class="language-java">public class WeatnerChartView extends View {
	private ArrayList&lt;WeatherItem&gt; items;
    private String unit;
    private String yFormat = "0";
 
    private Context context;
 
    public void SetTuView(ArrayList&lt;WeatherItem&gt; list, String unitInfo) {
        this.items = list;
        this.unit = unitInfo;
    }
 
    public WeatnerChartView(Context ct) {
        super(ct);
        this.context = ct;
    }
 
    public WeatnerChartView(Context ct, AttributeSet attrs) {
        super(ct, attrs);
        this.context = ct;
    }
 
    public WeatnerChartView(Context ct, AttributeSet attrs, int defStyle) {
        super(ct, attrs, defStyle);
        this.context = ct;
    }
 
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
 
        if (items == null) {
            return;
        }
 
        int height = getHeight();
        int width = getWidth();
 
        int split = dip2px(context, 8);
        int marginl = width / 24;//12
        int margint = dip2px(context, 60);
        int margint2 = dip2px(context, 25);
        int bheight = height - margint - 2 * split;
 
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#ffffff"));//#7fffffff


        paint.setStrokeWidth(4);
        paint.setStyle(Style.STROKE);
        // 画单位
        Paint p = new Paint();
        p.setAlpha(0x0000ff);
        p.setTextSize(sp2px(context, 15));
        p.setColor(Color.parseColor("#28bbff"));
        canvas.drawText(unit, split, margint2 + split * 2, p);
 
        // 画X坐标
        ArrayList&lt;Integer&gt; xlist = new ArrayList&lt;Integer&gt;();
        paint.setColor(Color.GRAY);
        for (int i = 0; i &lt; items.size(); i++) {
            int span = (width - 2 * marginl) / items.size();
            int x = marginl + span / 2 + span * i;
            xlist.add(x);
            drawText(items.get(i).getX(), x, split * 2, canvas);
        }
 
        float max = Float.MIN_VALUE;
        float min = Float.MAX_VALUE;
        for (int i = 0; i &lt; items.size(); i++) {
            float y = items.get(i).getY();
            if (y &gt; max) {
                max = y;
            }
            if (y &lt; min) {
                min = y;
            }
        }
 
        float span = max - min;
        if (span == 0) {
            span = 6.0f;
        }
        max = max + span / 6.0f;
        min = min - span / 6.0f;
 
        // 获取点集合
        Point[] mPoints = getPoints(xlist, max, min, bheight, margint);
 
        // 画线
        paint.setColor(Color.parseColor("#7fffffff"));
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(3);//8
        drawLine(mPoints, canvas, paint);
 
        // 画点
        paint.setColor(Color.parseColor("#28bbff"));
        paint.setStyle(Style.FILL);
        for (int i = 0; i &lt; mPoints.length; i++) {
            canvas.drawCircle(mPoints[i].x, mPoints[i].y, 8, paint);//radius=12
            String yText = new java.text.DecimalFormat(yFormat).format(items
                    .get(i).getY());
            drawText(yText+"°", mPoints[i].x,
                    mPoints[i].y - dip2px(context, 12), canvas);
        }
    }
 
    private Point[] getPoints(ArrayList&lt;Integer&gt; xlist, float max, float min,
            int h, int top) {
        Point[] points = new Point[items.size()];
        for (int i = 0; i &lt; items.size(); i++) {
            int ph = top + h
                    - (int) (h * ((items.get(i).getY() - min) / (max - min)));
            points[i] = new Point(xlist.get(i), ph);
        }
        return points;
    }
 
    private void drawLine(Point[] ps, Canvas canvas, Paint paint) {
        Point startp = new Point();
        Point endp = new Point();
        for (int i = 0; i &lt; ps.length - 1; i++) {
            startp = ps[i];
            endp = ps[i + 1];
            canvas.drawLine(startp.x, startp.y, endp.x, endp.y, paint);
        }
    }
 
    private void drawText(String text, int x, int y, Canvas canvas) {
        Paint p = new Paint();
        p.setAlpha(0x0000ff);
        p.setTextSize(sp2px(context, 15));
        p.setTextAlign(Paint.Align.CENTER);
        p.setColor(Color.WHITE);
        canvas.drawText(text, x, y, p);
    }
 
    public ArrayList&lt;WeatherItem&gt; getItems() {
        return items;
    }
 
    public void setItems(ArrayList&lt;WeatherItem&gt; items) {
        this.items = items;
    }
 
    public String getyFormat() {
        return yFormat;
    }
 
    public void setyFormat(String yFormat) {
        this.yFormat = yFormat;
    }
 
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
 
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}</code></pre> 
<span id="OSC_h2_1"></span>
