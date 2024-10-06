package com.mdshaz.blog.blog_rest_api1.services;

import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;

public interface LikeService 
{
    String addLike(Long userId, Long postId);
    String removeLike(Long userId, Long postId);
    long totalLikesOnPost(Long postId);
    boolean likeExist(User user,Post post);
}
