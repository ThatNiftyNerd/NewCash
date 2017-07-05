package com.house603.cash.feature.database.dto;

import com.house603.cash.feature.model.ArticlesItem;

/**
 * Created by Admin on 2/14/2017.
 */

public interface ArticleModelDTO {
    boolean AddArticle(ArticlesItem model);

    boolean UpdateArticle(ArticlesItem model);

    void DeleteArticle(String id);
}
