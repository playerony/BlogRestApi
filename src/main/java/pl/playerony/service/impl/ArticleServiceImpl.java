package pl.playerony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Article;
import pl.playerony.repository.ArticleRepository;
import pl.playerony.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	private ArticleRepository articleRepository;

	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository){
		this.articleRepository = articleRepository;
	}
	
	@Override
	public Boolean saveArticle(Article article) throws DatabaseException, InputException {
		if(article.getId() != null)
			return articleRepository.updateArticle(article.getId(), article);
		else
			return articleRepository.insertArticle(article);
	}

	@Override
	public Article selectArticle(Long id) throws DatabaseException {
		return articleRepository.selectArticleById(id);
	}

	@Override
	public List<Article> selectArticles() throws DatabaseException {
		return articleRepository.selectArticles();
	}

	@Override
	public Boolean removeArticle(Long id) throws DatabaseException {
		return articleRepository.removeArticle(id);
	}

}
