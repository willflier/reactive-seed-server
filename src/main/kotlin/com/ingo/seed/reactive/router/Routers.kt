package com.ingo.seed.reactive.router

import com.ingo.seed.reactive.handler.HelloHandler
import com.ingo.seed.reactive.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Routers(
    private val userHandler: UserHandler,
    private val helloHandler: HelloHandler
) {

    @Bean
    fun apiRouterDSL() = router {
        (accept(APPLICATION_JSON) and "/api").nest {
            //hello
            GET("/hello", helloHandler::getHello)

            //user
            "/user".nest {
                GET("/", userHandler::findAll)
                POST("/", userHandler::save)
                GET("/{userName}", userHandler::findByUsername)
                DELETE("/{userName}", userHandler::deleteByUsername)
            }
        }

    }
}