package cn.edu.gdmec.android.vicdemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.vicdemo.Activity.LoginActivity;
import cn.edu.gdmec.android.vicdemo.Activity.PlayHistoryActivity;
import cn.edu.gdmec.android.vicdemo.Activity.SettingActivity;
import cn.edu.gdmec.android.vicdemo.Activity.UserInfoActivity;
import cn.edu.gdmec.android.vicdemo.R;
import cn.edu.gdmec.android.vicdemo.utils.AnalysisUtils;

public class MyinfoFragment extends Fragment implements View.OnClickListener{
    private LinearLayout ll_head;
    private ImageView iv_head_icon;
    private TextView tv_user_name;
    //播放
    private RelativeLayout rl_course_history;
    private ImageView iv_course_history_icon;//
    private TextView tv_course_history;//
    //设置
    private RelativeLayout rl_setting;
    private ImageView iv_userInfo_icon;//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myinfo, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll_head = view.findViewById(R.id.ll_head);
        iv_head_icon = view.findViewById(R.id.iv_head_icon);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        rl_course_history = view.findViewById(R.id.rl_course_history);
        rl_setting = view.findViewById(R.id.rl_setting);
        //“我”的界面的头像部分，要实现两个功能
        //1.打开“我”的界面后要判断是否已登录，已登录显示用户名，未登录显示“点击登陆”。
        //2.头像部分点击后，会判断是否登录，如果登录了，则跳转到个人资料界面，
        // 如果没登录，则跳转到login页面。
        if (AnalysisUtils.readLoginStatus(getActivity())){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(getActivity()));
        }else {
            tv_user_name.setText("点击登录");
        }
        //添加监听器
        ll_head.setOnClickListener(this);
        rl_course_history.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_head:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到个人资料界面
                    Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    //跳转到登录界面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }
                break;
            case R.id.rl_course_history:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到播放记录页面
                    Intent intent = new Intent(getActivity(), PlayHistoryActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到设置界面
                    Intent intent=new Intent(getActivity(), SettingActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
// 未登录时，点击登陆打开登陆页面后，
// 需要MainActivity更新状态并显示，
// 所以用startActivityForResult()方法打开。
// 老样子，这边用startActivityForResult（）打开，
// MainActivity就要加上onActivityForResult()方法来执行响应。