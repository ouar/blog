package com.salah.blog.ouarse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import com.salah.blog.ouarse.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	@EntityGraph(value = "Post.postDetails", type = EntityGraphType.LOAD)
	List<Post> findAll();

}
