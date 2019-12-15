package com.salah.blog.ouarse.services;

import java.util.List;

import com.salah.blog.ouarse.dto.PostDto;
import com.salah.blog.ouarse.entities.Post;

public interface PostService {

	List<PostDto> getListPosts();

	void createPost(Post post);

	Post findById(int id);

}
