package pl.playerony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Comment;
import pl.playerony.service.CommentService;

@Controller
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/save")
	public @ResponseBody Map<String, Object> saveComment(@RequestBody Comment comment)
			throws DatabaseException, InputException {
		Map<String, Object> map = new HashMap<>();

		commentService.saveComment(comment);
		map.put("status", "success");

		return map;
	}

	@GetMapping("/all")
	public @ResponseBody Map<String, Object> selectAll() throws DatabaseException {
		Map<String, Object> map = new HashMap<>();

		map.put("comments", commentService.selectComments());
		map.put("status", "success");

		return map;
	}

	@GetMapping("/article/{id}")
	public @ResponseBody Map<String, Object> findAllByTopicId(@PathVariable("id") Long id) throws DatabaseException {
		Map<String, Object> map = new HashMap<>();

		map.put("comments", commentService.selectCommentsByArticleId(id));
		map.put("status", "success");

		return map;
	}

	@GetMapping("/get/{id}")
	public @ResponseBody Map<String, Object> selectComment(@PathVariable("id") Long id) throws DatabaseException {
		Map<String, Object> map = new HashMap<>();

		map.put("comment", commentService.selectComment(id));
		map.put("status", "success");

		return map;
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> removeComment(@PathVariable("id") Long id) throws DatabaseException
	{
		Map<String, Object> map = new HashMap<>();
		
		commentService.removeComment(id);
		map.put("status", "success");
		
		return map;
	}

}
