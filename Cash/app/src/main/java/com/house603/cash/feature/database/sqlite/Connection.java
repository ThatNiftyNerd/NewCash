package com.house603.cash.feature.database.sqlite;

import android.content.Context;

import com.house603.cash.feature.model.ArticlesItem;
import com.house603.cash.feature.model.CurrencyModel;
import com.j256.ormlite.dao.Dao;


/**
 * Created by Admin on 8/25/2016.
 */
public class Connection extends OrmliteDbHelper {
    private static Connection instance;
    private Dao<ArticlesItem, Integer> articleDao = null;


    public Connection(Context context){
    super(context);
    }
    public static Connection getInstance(Context context) {
        if (instance == null) {
            instance = new Connection(context);
        }
        return instance;
    }
    /**
     * Returns the Database Access Object (DAO) for our UserModel class. It will
     * create it or just give the cached value.
     *
     * @throws java.sql.SQLException
     */
    public Dao<ArticlesItem, Integer> getArticleDataDao()
            throws java.sql.SQLException {
        if (articleDao == null) {
            articleDao = getDao(ArticlesItem.class);
        }
        return articleDao;
    }




    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        articleDao = null;

    }


}
