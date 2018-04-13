package cn.edu.gdmec.android.vicdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.vicdemo.utils.AnalysisUtils;

/**
 * Created by Jack on 2018/4/11.
 */

public class SettingActivity extends Activity implements View.OnClickListener{
    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;
    //标题栏
    private RelativeLayout title_bar;
    private TextView tv_back;
    private TextView tv_main_title;
    //settingActivity.java，给它创个instance
    public static SettingActivity instance=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance=this;
        initView();
    }
    private void initView(){
        rl_modify_psw=findViewById(R.id.rl_modify_psw);
        rl_security_setting=findViewById(R.id.rl_security_setting);
        rl_exit_login=findViewById(R.id.rl_exit_login);
        //标题栏
        title_bar=findViewById(R.id.title_bar);
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        //给tv_main_title设置个文本，给title_bar加个背景
        tv_main_title.setText("设置");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        //添加监听器
        tv_back.setOnClickListener(this);
        rl_modify_psw.setOnClickListener(this);
        rl_security_setting.setOnClickListener(this);
        rl_exit_login.setOnClickListener(this);
    }
    //给设置界面添加按钮响应
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                SettingActivity.this.finish();
                break;
            case R.id.rl_modify_psw:
                //修改密码界面
                break;
            case R.id.rl_security_setting:
                //设置密保界面
                break;
            case R.id.rl_exit_login:
                //退出登录，即清除登录状态
                Toast.makeText(this,"退出登录成功",Toast.LENGTH_SHORT).show();
                AnalysisUtils.cleanLoginStatus(this);
                Intent data=new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                finish();
                break;
        }
    }
}
