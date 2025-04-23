package com.desafio.batch.estudos.repository;

import com.desafio.batch.estudos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
