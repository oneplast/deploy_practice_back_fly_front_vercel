package io.river.backend.domain.post.post.service

import io.river.backend.domain.post.post.entity.Post
import io.river.backend.domain.post.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun count() = postRepository.count()

    fun write(title: String, content: String): Post {

        return postRepository.save(Post(title = title, content = content))
    }

    @Transactional(readOnly = true)
    fun findAll() = postRepository.findAll()
}