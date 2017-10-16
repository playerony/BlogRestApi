package pl.playerony.service;

import java.util.List;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Comment;

public interface CommentService {
	Boolean saveComment(Comment comment) throws DatabaseException, InputException;
	
	Comment selectComment(Long id) throws DatabaseException;
	
	List<Comment> selectComments() throws DatabaseException;
	
	List<Comment> selectCommentsByArticleId(Long articleId) throws DatabaseException;
	
	Boolean removeComment(Long id) throws DatabaseException;
}
