package cn.edu.gdmec.android.vicdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jack on 2018/3/27.
 * 注册界面主要用于用户输入注册信息，在注册界面中用户需要输入用户名，密码，再次输入密码（确保密码输入无误）
 * 当点击注册按钮时进行用户注册
 * 使用本地数据，注册成功后，用户名和密码用SharedPreferences保存
 * 用MD5进行加密
 * <p>
 * 重点：
 * ImageView，EditText控件，Button控件
 * SharedPreferences的使用
 * setResult(RESULT_OK,data)方法的使用
 * MD5加密
 * <p>
 * 学习要点：
 * 掌握注册界面的设计和逻辑构思
 * 掌握标题栏的创建和常用控件的使用
 * 通过SharedPreferences实现数据的存取功能
 * setResult()方法实现界面间的数据回传
 * 通过MD5加密
 */

public class RegisterActivity extends Activity implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private EditText et_user_name;
    private EditText et_psw;
    private EditText et_psw_again;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    private EditText getEtUserName() {
        return (EditText) findViewById(R.id.et_user_name);
    }

    private EditText getEtPsw() {
        return (EditText) findViewById(R.id.et_psw);
    }

    private EditText getEtPswAgain() {
        return (EditText) findViewById(R.id.et_psw_again);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                //TODO implement
                break;
        }
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_psw_again = (EditText) findViewById(R.id.et_psw_again);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String psw = et_psw.getText().toString().trim();
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String again = et_psw_again.getText().toString().trim();
        if (TextUtils.isEmpty(again)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}

