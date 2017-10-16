package pl.playerony.service;

import java.util.List;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Article;

public interface ArticleService {
	Boolean saveArticle(Article article) throws DatabaseException, InputException;
	
	Article selectArticle(Long id) throws DatabaseException;
	
	List<Article> selectArticles() throws DatabaseException;
	
	Boolean removeArticle(Long id) throws DatabaseException;
}
