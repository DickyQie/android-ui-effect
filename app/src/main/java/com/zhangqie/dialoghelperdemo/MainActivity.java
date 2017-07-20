package com.zhangqie.dialoghelperdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CenterDialog.OnCenterItemClickListener, BottomDialog.OnBottomMenuItemClickListener{

    private CenterDialog centerDialog;
    private BottomDialog bottomDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        centerDialog = new CenterDialog(this, R.layout.dialog_center_layout, new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);

        bottomDialog = new BottomDialog(this, R.layout.dialog_bottom_layout, new int[]{R.id.pick_photo_album, R.id.pick_photo_camera, R.id.pick_photo_cancel});
        bottomDialog.setOnBottomMenuItemClickListener(this);
    }

    String[] string=new String[]{"Android","Web","Java"};
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                DialogHelper.getWaitDialog(this,"加载中...").show();
                break;
            case R.id.btn2:
               DialogHelper.getMessageDialog(this, "我是信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"基本对话框，并监听确定按钮",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.btn3:
                DialogHelper.getMessageDialog(this,"单信息对话框  没有监听按钮").show();//单信息对话框  没有监听按钮
                break;
            case R.id.btn4:
                DialogHelper.getSelectDialog(this, string, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,string[which],Toast.LENGTH_LONG).show();
                    }
                }).show();

                break;
            case R.id.btn5:
                DialogHelper.getConfirmDialog(this, "监听确定按钮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.btn6:
                DialogHelper.getConfirmDialog(this, "监听确定和取消按钮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_LONG).show();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Clear",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.btn7:
                DialogHelper.getConfirmDialog(this, "标题", "消息", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_LONG).show();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.btn8:
               DialogHelper.getConfirmDialog(this, "", "消息", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_LONG).show();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_LONG).show();
                    }
                }).show();

                break;
            case R.id.btn9:
                DialogHelper.getSingleChoiceDialog(this, "标题", string, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, string[which], Toast.LENGTH_LONG).show();//用于得到数组选中项的数据
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_LONG).show();
                    }
                }).show();
                break;
            case R.id.btn10:
                /***
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
                builder1.show();

                break;
            case R.id.btn11:
                centerDialog.show();
                TextView dialog_sure = (TextView) centerDialog.findViewById(R.id.dialog_sure);
                dialog_sure.setText("添加图片");
                break;
            case R.id.btn12:
                bottomDialog.show();
                break;
        }
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()){
            case R.id.dialog_sure:
                Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_cancel:
                Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBottomMenuItemClick(BottomDialog dialog, View view) {
        switch (view.getId()){
            case R.id.pick_photo_album:
                Toast.makeText(MainActivity.this,"从相册选取",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pick_photo_camera:
                Toast.makeText(MainActivity.this,"拍照",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
