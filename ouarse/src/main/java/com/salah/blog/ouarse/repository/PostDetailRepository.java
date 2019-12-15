package com.salah.blog.ouarse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salah.blog.ouarse.entities.PostDetail;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer> {

}
