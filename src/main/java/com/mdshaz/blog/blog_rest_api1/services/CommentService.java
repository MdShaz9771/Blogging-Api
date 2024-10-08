package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.CommentResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CommentRequestDto;

public interface CommentService
{
	CommentResponseDto addComment(CommentRequestDto commentReqDto,Long userId,Long postId);
	List<CommentResponseDto> findAllCommentByPostId(Long postId);
	CommentResponseDto findCommentById(Long commentId);
	void removeComment(Long commentId);
	Long getTotalComments(Long postId);

}
