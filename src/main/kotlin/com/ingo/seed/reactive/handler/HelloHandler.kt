package com.ingo.seed.reactive.handler

import com.ingo.seed.reactive.util.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.toMono
import org.springframework.web.reactive.function.server.*


@Component
class HelloHandler {
    fun getHello(serverRequest: ServerRequest) = ok().json().body<String>("Hello Reactive World!".toMono())
}