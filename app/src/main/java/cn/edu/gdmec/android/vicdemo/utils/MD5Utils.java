package cn.edu.gdmec.android.vicdemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jack on 2018/3/27.
 * 创建一个md5()方法
 * 过 MessageDigest 的 getInstance()方法
 * 获取数据加密对象 digest，然后通过该对象的 digest()方法对密码进行加密
 */

public class MD5Utils {
    // md5 加密的算法
    public static String md5(String text){
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b: result){
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    sb.append("0"+hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
