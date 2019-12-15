package com.salah.blog.ouarse.services;

import java.util.List;

import com.salah.blog.ouarse.dto.PostDetailDto;
import com.salah.blog.ouarse.entities.PostDetail;

public interface PostDetailService {

	List<PostDetailDto> getListPostDetails(int idPost);

	void createPostDetail(PostDetail postDetail);

}
