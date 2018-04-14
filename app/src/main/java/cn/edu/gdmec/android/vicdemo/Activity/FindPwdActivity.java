package cn.edu.gdmec.android.vicdemo.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.vicdemo.R;
import cn.edu.gdmec.android.vicdemo.utils.AnalysisUtils;
import cn.edu.gdmec.android.vicdemo.utils.MD5Utils;

/**
 * Created by Jack on 2018/4/14.
 * tv_user_name
 * et_user_name
 * et_validate_name
 * tv_reset_psw
 * et_reset_psw
 * btn_validate
 */

public class FindPwdActivity extends Activity implements View.OnClickListener{
    //标题栏
    private RelativeLayout title_bar;
    private TextView tv_back;
    private TextView tv_main_title;
    //
    private TextView tv_user_name;
    private EditText et_user_name;
    private EditText et_validate_name;
    private TextView tv_reset_psw;

    private Button btn_validate;
    private String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        from = getIntent().getStringExtra("from");
        initView();
    }

    private void initView() {
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        title_bar=findViewById(R.id.title_bar);
        //
        tv_user_name=findViewById(R.id.tv_user_name);
        et_user_name=findViewById(R.id.et_user_name);
        et_validate_name=findViewById(R.id.et_validate_name);
        tv_reset_psw=findViewById(R.id.tv_reset_psw);
        btn_validate=findViewById(R.id.btn_validate);
        if ("security".equals(from)){
            tv_main_title.setText("设置密保");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPwdActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_validate:
                submit();
                break;
        }
    }

    private void submit() {
        String validateName = et_validate_name.getText().toString().trim();
        if ("security".equals(from)){
            if (TextUtils.isEmpty(validateName)){
                Toast.makeText(this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                return;
            }else {
                Toast.makeText(this,"密保设置成功",Toast.LENGTH_SHORT).show();
                saveSecurity(validateName);
                FindPwdActivity.this.finish();
            }
        }else {
            String name=et_user_name.getText().toString().trim();
            String sp_security=readSecurity(name);
            if (TextUtils.isEmpty(name)){
                Toast.makeText(this,"请输入您的用户名",Toast.LENGTH_SHORT).show();
                return;
            }else if (!isExistUserName(name)){
                Toast.makeText(this,"您输入的用户名不存在",Toast.LENGTH_SHORT).show();
                return;
            }else if (TextUtils.isEmpty(validateName)){
                Toast.makeText(this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                return;
            }else if (!validateName.equals(sp_security)){
                Toast.makeText(this,"输入的密保不正确",Toast.LENGTH_SHORT).show();
                return;
            }else {
                tv_reset_psw.setVisibility(View.VISIBLE);
                tv_reset_psw.setText("初始密码：123456");
                savePsw(name);
            }
        }
    }

    private void savePsw(String name){
        String md5Psw= MD5Utils.md5("123456");
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name,md5Psw);
        editor.commit();
    }


    private boolean isExistUserName(String name){
        boolean hasUserName=false;
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sharedPreferences.getString(name,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName=true;
        }
        return hasUserName;
    }
    private String readSecurity(String name){
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security=sharedPreferences.getString(name+"_security","");
        return security;
    }
    private void saveSecurity(String validateName) {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
}
