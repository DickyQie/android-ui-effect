# Android之UI--打造12种Dialog对话框 
<div class="blog-body" id="blogBody">
                                    <val data-name="blog_content_type" data-value="richtext"></val>
                    <div class='BlogContent'>
                        <p>最近有空，来把app中常用到的Dialog对话框写一篇博客，在app中很多地方都会用到Dialog对话框，今天小编我就给大家介绍Dialog对话框。</p> 
<p>先看看效果图：</p> 
<p>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720110659099-1579240136.png"></p> 
<p>12种，可根据需求选择，上图可知，底部弹框也可通过Dialog对话框实现，可以不用PopupWindow来实现了，比较方便</p> 
<p>&nbsp;</p> 
<p>Android Support Library v22.1 中开始提供了 Material 风格的 Dialog 控件 ，看起来也是很好看的</p> 
<p>Material 风格的 Dialog类：android.support.v7.app.AlertDialog</p> 
<p>这个 V7 包中的 AlertDialog 在 Android 2.1 以上可以提供兼容性的 Material 风格 Dialog</p> 
<p>举例说明一下：</p> 
<pre><code class="language-java">public static AlertDialog.Builder getMessageDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle("我是标题");
        builder.setMessage(message);/*消息内容*/
        builder.setIcon(R.mipmap.ic_launcher);/*logo图标*/
        builder.setPositiveButton("确定", onClickListener);/*确定事件监听*/
　　     builder.setNegativeButton("取消", null);/*取消事件（可以监听或不监听（关闭窗口））*/
        return builder;
    }</code></pre> 
<p>&nbsp;使用调用</p> 
<pre><code class="language-java"> DialogHelper.getMessageDialog(this, "我是信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"基本对话框，并监听确定按钮",Toast.LENGTH_LONG).show();
                    }
                }).show();</code></pre> 
<p>效果如图：</p> 
<p><img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720112219583-984097089.png"></p> 
<p>&nbsp;</p> 
<p><strong><span style="color:#008080">进度条对话框</span></strong></p> 
<p><img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720112637318-447769960.gif"></p> 
<pre><code class="language-java">/***
     * 获取一个进度对话框(耗时操作使用)
     *
     * @param context
     * @param message 加载提示信息
     * @return
     */
    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        return waitDialog;
    }</code></pre> 
<p><span style="color:#008080"><strong>只含信息和监听确定和取消按钮，选择对话框</strong></span></p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720113203443-2019444316.png">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720113219208-1020597647.png"></p> 
<p>&nbsp;</p> 
<p><span style="color:#008080"><strong>单选对话框&nbsp; 和自定义对话框</strong></span>（有时候自带的各种方法并不能满足我们的Dialog的设计需求，这时候我们可以自己写一个 xml 设计符合需求的Dialog。（仅为设计Dialog的Message部分，并不是Dialog整体））</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720113837146-1503393432.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720113859099-568621095.png"></p> 
<pre><code class="language-java"> /***
     *单选对话框
     * @param context
     * @param title  标题
     * @param arrays  数组信息
     * @param selectIndex  默认选中
     * @param onClickListener  单选按钮选择事件
     * @param onOkClickListener  确定按钮事件
     * @return
     */
    public static AlertDialog.Builder getSingleChoiceDialog(Context context, String title, String[] arrays, int selectIndex, DialogInterface.OnClickListener onClickListener,  DialogInterface.OnClickListener onOkClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setSingleChoiceItems(arrays, selectIndex, onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setPositiveButton("确定", onOkClickListener);
        builder.setNegativeButton("取消", null);//null===关闭对话框
        return builder;
    }</code></pre> 
<pre><code class="language-java">  /***
                 * 自定义对话框
                 */
           LayoutInflater inflater = getLayoutInflater();
           View   dialog = inflater.inflate(R.layout.dialog_normal_layout,(ViewGroup) findViewById(R.id.dialog));
           final EditText editText = (EditText) dialog.findViewById(R.id.et);
           AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
           builder1.setTitle("这里是Title");
           builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
               }
               });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.setView(dialog);
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.show();</code></pre> 
<p><span style="color:#008080"><strong>app经常需要的如下弹窗，四周是圆角，IOS几乎都是这个效果，底部对话框也是（照片选择或者分享），</strong></span></p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720114343552-447487178.png">&nbsp;&nbsp; <img alt="" height="567" src="http://images2015.cnblogs.com/blog/1041439/201707/1041439-20170720110659099-1579240136.png" width="308"></p> 
<p>&nbsp;</p> 
<p>以上两种方式需要自定义Dialog类来实现此功能，使用&nbsp; <strong><span style="color:#008080">android.app.Dialog 类即可&nbsp; </span></strong><span style="color:#000000">创建类继承Dialog来实现。</span></p> 
<p>&nbsp;</p> 
<p><span style="color:#000000">由于代码太多，就不一一贴出来了，直接下载即可</span></p>
<p>&nbsp;</p> 
<p><strong><span style="color:#008080">不足之处请留言指正！有问题的可以给我留言！谢谢！</span></strong></p> 
