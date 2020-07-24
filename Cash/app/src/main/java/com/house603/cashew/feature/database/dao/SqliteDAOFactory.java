package com.house603.cashew.feature.database.dao;

import android.content.Context;

import com.house603.cashew.feature.database.sqlite.Connection;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class SqliteDAOFactory
    extends DAOFactory {

    public SqliteDAOFactory(Context context) {
        super(context);
    }


    public Connection getConnection() {
        if (mConnection == null) {
            mConnection = OpenHelperManager.getHelper(mContext, Connection.class);
        }
        return mConnection;
    }

    public void closeConnection() {
        if (mConnection != null) {
            OpenHelperManager.releaseHelper();
            mConnection = null;
        }
    }
}
