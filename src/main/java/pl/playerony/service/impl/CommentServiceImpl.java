package pl.playerony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Comment;
import pl.playerony.repository.CommentRepository;
import pl.playerony.service.CommentService;

public class CommentServiceImpl implements CommentService {
	private CommentRepository commentRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository)
	{
		this.commentRepository = commentRepository;
	}

	
	@Override
	public Boolean saveComment(Comment comment) throws DatabaseException, InputException {
		if(comment.getId() != null)
			return commentRepository.updateComment(comment.getId(), comment);
		else
			return commentRepository.insertComment(comment);
	}

	@Override
	public Comment selectComment(Long id) throws DatabaseException {
		return commentRepository.selectCommentById(id);
	}

	@Override
	public List<Comment> selectComments() throws DatabaseException {
		return commentRepository.selectComments();
	}

	@Override
	public List<Comment> selectCommentsByArticleId(Long articleId) throws DatabaseException {
		return commentRepository.selectCommentsByArticleId(articleId);
	}

	@Override
	public Boolean removeComment(Long id) throws DatabaseException {
		return commentRepository.removeComment(id);
	}

}
