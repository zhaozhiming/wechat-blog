package com.zzm.wechat.builder;

import com.zzm.wechat.model.wechat.Article;

public class ArticleBuilder {
    private String title;

    public static ArticleBuilder aArticle() {
        return new ArticleBuilder();
    }

    public ArticleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public Article build() {
        Article article = new Article();
        article.setTitle(title);
        return article;
    }
}
