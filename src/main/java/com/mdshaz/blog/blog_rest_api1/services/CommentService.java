package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.CommentDto;

public interface CommentService
{
	CommentDto addComment(CommentDto commentDto,Long userId,Long postId);
	List<CommentDto> findAllCommentByPostId(Long postId);
	CommentDto findCommentById(Long commentId);
	void removeComment(Long commentId);
	Long getTotalComments(Long postId);

}
