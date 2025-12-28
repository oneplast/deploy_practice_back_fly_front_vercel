package io.river.backend.domain.post.post.repository

import io.river.backend.domain.post.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>