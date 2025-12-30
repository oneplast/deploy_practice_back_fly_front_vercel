package io.river.backend.domain.post.post.controller

import io.river.backend.domain.post.post.service.PostService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiV1PostControllerTest {

    @Autowired
    private lateinit var postService: PostService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DisplayName("다건 조회")
    fun t1() {
        val resultActions = mvc
            .perform(
                get("/api/v1/posts")
            )
            .andDo(print())

        val posts = postService.findAll()

        resultActions
            .andExpect(handler().handlerType(ApiV1PostController::class.java))
            .andExpect(handler().methodName("getItems"))
            .andExpect(status().isOk())

        for (i in posts.indices) {
            val post = posts[i]

            resultActions
                .andExpect(jsonPath("$[$i].id").value(post.id))
                .andExpect(jsonPath("$[$i].title").value(post.title))
                .andExpect(jsonPath("$[$i].content").value(post.content))
        }
    }
}