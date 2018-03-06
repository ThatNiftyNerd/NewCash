package com.house603.cashew.feature.database.dao;

import android.content.Context;

import com.house603.cashew.feature.database.dto.ArticleModelDTO;
import com.house603.cashew.feature.database.sqlite.Connection;
import com.house603.cashew.feature.model.ArticlesItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/8/2016.
 */

public class ArticleItemDAO
    implements ArticleModelDTO {
    private Connection connection;
    private Context mContext;

    public ArticleItemDAO(Context context, Connection connection){
        this.mContext= context;
        this.connection = connection;
    }

    @Override
    public boolean AddArticle(ArticlesItem model) {
        try {

            return connection.getArticleDataDao().create(model) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean UpdateArticle(ArticlesItem model) {
        try {
            return connection.getArticleDataDao().update(model)==1;
        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void DeleteArticle(String id) {
        try {
            connection.getArticleDataDao().deleteById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<ArticlesItem> getAllReminderModels() {
        try {
            return connection.getArticleDataDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    public boolean checkContactAlreadyExistInDb(ContactModel model){
//        List<ReminderModel> all = getReminderModels();
//        for(ReminderModel child : all){
//            if(child.getmPhoneId() ==  model.getmPhoneId()){
//                return true;
//            }
//        }
//        return false;
//    }
    public ArticlesItem getContactModelByID(int id) {
        try {
            return connection.getArticleDataDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
