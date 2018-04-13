package cn.edu.gdmec.android.vicdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jack on 2018/4/10.
 * 工具类
 * 多次用到sharedPreferences共享参数去存储用户的登录状态或清除登录状态，
 * “我”的界面也要求用到读取用户姓名的方法，所以我们干脆把这三个方法都扔到AnalysisUtils里面吧
 */

public class AnalysisUtils {
    //读取用户名
    public static String readLoginUserName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sharedPreferences.getString("loginUserName","");
        return userName;
    }

    //读取登录状态
    public static boolean readLoginStatus(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }

    //清除登录状态
    public static void cleanLoginStatus(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }
}
