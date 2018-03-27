package cn.edu.gdmec.android.vicdemo;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 *
 * 注册界面逻辑代码
 * 需要获取用户名，用户密码和再次确认密码
 * 当两次密码相同时，将用户名和密码（经过 MD5 加密）保存到 SharedPreferences 中
 * 当注册成功之后需要将用户名传递到登录界面 LoginActivity 中
 */

public class RegisterActivity extends AppCompatActivity {
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    //用户名、密码、再次输入的密码的控件
    private EditText et_user_name;
    private EditText et_psw;
    private EditText et_psw_again;
    private Button btn_register;
    //用户名、密码、再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    //标题布局
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();

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


    private void initView() {
        //从 main_title_bar.xml 页面布局中获得对应的 UI 控件
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar=(RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);

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

