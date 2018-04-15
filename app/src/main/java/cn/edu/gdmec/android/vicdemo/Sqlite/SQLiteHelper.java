package cn.edu.gdmec.android.vicdemo.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jack on 2018/4/15.
 */

public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "bxg.db";
    public static final String U_USER_INFO = "userInfo";

    /*public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/
    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ U_USER_INFO + "("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "userName VARCHAR, "
        + "sex VARCHAR, "
        + "signature VARCHAR, "
        + "qq VARCHAR "
        + ")");
    }

    /**
     * 当数据库版本号增加才会调用此方法
     **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USER_INFO);
        onCreate(db);
    }
}