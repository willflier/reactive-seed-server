package com.ingo.seed.reactive.handler

import com.ingo.seed.reactive.service.UserService
import com.ingo.seed.reactive.util.json
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.*

@Component
class UserHandler(
    val userService: UserService
) {
//    fun save(req: ServerRequest) = userService.save(req.bodyToMono()).flatMap {
//        ok().json().body(it.toMono())
//    }
    fun save(req: ServerRequest) = ok().json().body(userService.save(req.bodyToMono()))

    fun deleteByUsername(req: ServerRequest) = ok().json().body(userService.deleteByUserName(req.pathVariable("userName")))

    fun findByUsername(req: ServerRequest) = ok().json().body(userService.findByUserName(req.pathVariable("userName")))

    fun findAll(req: ServerRequest) = ok().json().body(userService.findAll())

}

