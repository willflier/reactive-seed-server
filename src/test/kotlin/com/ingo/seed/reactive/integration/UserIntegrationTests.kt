package com.ingo.seed.reactive.integration

import com.ingo.seed.reactive.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.test.test
import java.util.*


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTests(@LocalServerPort port: Int) {

    private val client = WebClient.create("http://localhost:$port")

    @Test
    fun `Create a new user`() {
        client.post().uri("/api/user/").accept(APPLICATION_JSON).contentType(APPLICATION_JSON)
                .syncBody(User(name = "Yo", userName = "Acker", phone = "18578754888", email = "acker@ingo.com", birthday = Date()))
                .retrieve()
                .bodyToMono<User>()
                .test()
                .consumeNextWith { assertEquals("Acker", it.userName) }
                .verifyComplete()
    }

    @Test
    fun `Find Acker`() {
        client.get().uri("/api/user/Acker").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono<User>()
                .test()
                .consumeNextWith {
                    assertEquals("Acker", it.userName)
                    assertEquals("Yo", it.name)
                    assertEquals("18578754888", it.phone)
                }
                .verifyComplete()
    }


    @Test
    fun `Find all users`() {
        client.get().uri("/api/user/").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux<User>()
                .test()
                .expectNextCount(7)
                .verifyComplete()
    }


}
