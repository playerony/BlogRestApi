package pl.playerony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Article;
import pl.playerony.service.ArticleService;

@Controller
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String, Object> saveArticle(@RequestBody Article article) throws DatabaseException, InputException {
		Map<String, Object> map = new HashMap<>();
		
		articleService.saveArticle(article);
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/all")
	public @ResponseBody Map<String, Object> selectAll() throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("articles", articleService.selectArticles());
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody Map<String, Object> selectArticle(@PathVariable("id") Long id) throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("article", articleService.selectArticle(id));
		map.put("status", "success");
		
		return map;
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> removeArticle(@PathVariable("id") Long id) throws DatabaseException
	{
		Map<String, Object> map = new HashMap<>();
		
		articleService.removeArticle(id);
		map.put("status", "success");
		
		return map;
	}
	
}
