package br.com.scrummob.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hlandim on 17/06/15.
 */
public class ScrumMobSQLHelper extends SQLiteOpenHelper {

    public static final String TAG = "SQLHelper";
    public static final String NOME_BANCO = "scummob";
    public static final int VERSAO_BANCO = 1;

    public ScrumMobSQLHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCrete");
        db.execSQL(AtividadeDao.getSqlCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade");
    }
}
