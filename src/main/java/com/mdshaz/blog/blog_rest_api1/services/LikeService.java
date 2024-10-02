package com.mdshaz.blog.blog_rest_api1.services;

public interface LikeService 
{
    String addLike(Long userId, Long postId);
    String removeLike(Long userId, Long postId);
    long totalLikesOnPost(Long postId);
}
