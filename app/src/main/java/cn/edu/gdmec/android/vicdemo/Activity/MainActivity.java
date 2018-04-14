package cn.edu.gdmec.android.vicdemo.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.vicdemo.Fragment.CourseFragment;
import cn.edu.gdmec.android.vicdemo.Fragment.ExercisesFragment;
import cn.edu.gdmec.android.vicdemo.Fragment.MyinfoFragment;
import cn.edu.gdmec.android.vicdemo.R;
import cn.edu.gdmec.android.vicdemo.utils.AnalysisUtils;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    //来自main_title_bar.xml
    private TextView tv_main_title;//标题
    private TextView tv_back;//返回按钮
    private RelativeLayout title_bar;// android:id="@+id/title_bar"
    //来自activity_main.xml
    private RelativeLayout main_body;
    private TextView bottom_bar_text_course;
    private ImageView bottom_bar_image_course;
    private TextView bottom_bar_text_exercises;
    private ImageView bottom_bar_image_exercises;
    private ImageView bottom_bar_image_myinfo;
    private TextView bottom_bar_text_myinfo;
    private LinearLayout main_bottom_bar;
    //  android:id="@+id/main_body"
    //  android:id="@+id/bottom_bar_course_btn" 按钮
    // android:id="@+id/bottom_bar_text_course"
    // android:id="@+id/bottom_bar_image_course"
    // android:id="@+id/title_bar";
    /*main_exercises_icon.png、
    main_course_icon.png、
    main_my_icon.png
    main_exercises_icon_selected.png、
    main_course_icon_selected.png、
    main_my_icon_selected.png*/
    //private View mCourseBtn,mExercisesBtn,mMyInfoBtn;
    private RelativeLayout bottom_bar_course_btn;
    private RelativeLayout bottom_bar_exercises_btn;
    private RelativeLayout bottom_bar_myinfo_btn;

    protected long exitTime;
    //给MainActivity加上退出清除登陆状态的方法。
    // 连续点击返回两次则退出，两次点击间隔超过2秒则提示再按一次退出。
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                this.finish();
                if (AnalysisUtils.readLoginStatus(this)){
                    AnalysisUtils.cleanLoginStatus(this);
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    //onActivityResult();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断从LoginActivity传过来登陆状态，并执行响应动作。
        if (data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            //从登录活动获得isLogin==true,从设置活动获得isLogin==false，他们的请求码都是1
            //之后还可以根据请求码和结果码完成更多需求
            if (isLogin){
                //课程
                setSelectStatus(0);
            }else {
                //我
                setSelectStatus(2);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setMain();
    }
    //用于打开初始页面
    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new CourseFragment()).commit();
    }

    private void setSelectStatus(int index) {
        switch (index){
            case 0:
                //图片点击选择变换图片，颜色的改变，其他变为原来的颜色，并保持原有的图片
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon_selected);
                bottom_bar_text_course.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new CourseFragment()).commit();
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new ExercisesFragment()).commit();
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                tv_main_title.setText("博学谷");
                break;
        }
    }

    private void initView() {
        //标题显示
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        title_bar=findViewById(R.id.title_bar);
        //底部导航栏
        main_body=findViewById(R.id.main_body);
        bottom_bar_text_course=findViewById(R.id.bottom_bar_text_course);
        bottom_bar_image_course=findViewById(R.id.bottom_bar_image_course);
        bottom_bar_text_exercises=findViewById(R.id.bottom_bar_text_exercises);
        bottom_bar_image_exercises=findViewById(R.id.bottom_bar_image_exercises);
        bottom_bar_text_myinfo=findViewById(R.id.bottom_bar_text_myinfo);
        bottom_bar_image_myinfo=findViewById(R.id.bottom_bar_image_myinfo);
        //包含底部 android:id="@+id/main_bottom_bar"
        main_bottom_bar=findViewById(R.id.main_bottom_bar);
        //View Btn android:id="@+id/bottom_bar_course_btn" 每个RelativeLayout
        bottom_bar_course_btn=findViewById(R.id.bottom_bar_course_btn);
        bottom_bar_exercises_btn=findViewById(R.id.bottom_bar_exercises_btn);
        bottom_bar_myinfo_btn=findViewById(R.id.bottom_bar_myinfo_btn);

        bottom_bar_course_btn.setOnClickListener(this);
        bottom_bar_exercises_btn.setOnClickListener(this);
        bottom_bar_myinfo_btn.setOnClickListener(this);
        //技巧
        //tv_back.setVisibility(View.GONE);
        tv_main_title.setText("博学谷课程");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
                //getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new CourseFragment()).commit();
                setSelectStatus(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                //getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new ExercisesFragment()).commit();
                setSelectStatus(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                //getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                setSelectStatus(2);
                break;
        }
    }
}

