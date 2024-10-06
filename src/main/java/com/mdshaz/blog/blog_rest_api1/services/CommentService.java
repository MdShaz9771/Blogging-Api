package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.CommentDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CommentRequestDto;

public interface CommentService
{
	CommentDto addComment(CommentRequestDto commentReqDto,Long userId,Long postId);
	List<CommentDto> findAllCommentByPostId(Long postId);
	CommentDto findCommentById(Long commentId);
	void removeComment(Long commentId);
	Long getTotalComments(Long postId);

}
