package cn.edu.gdmec.android.vicdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jack on 2018/3/27.
 */

public class SplashActivity extends AppCompatActivity{
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

    }

    private void init() {
        textView = findViewById(R.id.tv_version);

        try {
            //获取程序包信息
            //通过PackageManager的getPackageInfo()方法获取PackageInfo对象
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            textView.setText("version:"+info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            textView.setText("V");
        }

        //利用 Timer 让此界面延迟 3 秒后再跳转,timer 中有一个线程,这个线程不断执行 timerTask
        Timer timer = new Timer();
        //timerTask 实现 runnable 接口,TimerTask 类表示一个在指定时间内执行的 timerTask
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        //Timer 的schedule()方法是任务调度方法 调度 TimerTask 执行跳转操作，实现延迟跳转功能
        timer.schedule(timerTask,3000);//设置这个 task 在延迟三秒之后自动执行
    }

}
