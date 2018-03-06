package com.house603.cashew.feature.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.house603.cashew.feature.model.ArticlesItem;
import com.house603.cashew.feature.model.CurrencyModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by Admin on 8/25/2016.
 */

public class OrmliteDbHelper extends OrmLiteSqliteOpenHelper {
 private static  final String DATABASE_NAME = "cashew.db";
    private  static final int DATABASE_VERSION = 1;

    //DAO classes
    private Dao<CurrencyModel, Integer> mContactModel = null;
    private RuntimeExceptionDao<ArticlesItem, ?> m;


    public OrmliteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
     try{
         Log.i(OrmliteDbHelper.class.getName(), "onCreate");
         TableUtils.createTable(connectionSource,CurrencyModel.class);
        // TableUtils.createTable(connectionSource,SimContactModel.class);

     }catch (SQLException e){
         Log.e(OrmliteDbHelper.class.getName(), "Can't create database", e);
         throw new RuntimeException(e);
     }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(OrmliteDbHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, ArticlesItem.class, true );
            onCreate(database,connectionSource);
        }catch(SQLException e){
            Log.e(OrmliteDbHelper.class.getName(), "Can't drop database", e);
            throw new RuntimeException(e);
        }
    }



    @Override
    public void close() {
        super.close();
    }
}
