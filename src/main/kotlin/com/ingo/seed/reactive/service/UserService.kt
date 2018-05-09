package com.ingo.seed.reactive.service

import com.ingo.seed.reactive.model.User
import com.ingo.seed.reactive.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class UserService(
    val userRepository: UserRepository
) {
    /**
     * 保存或更新。
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     */
    fun save(user: Mono<User>): Mono<User> = user.flatMap { usr ->
            userRepository.findByUserName(usr.userName)
                .flatMap { userRepository.save(usr.copy(id = it.id).toMono()) }
                .switchIfEmpty(userRepository.save(usr.toMono()))
        }

    /**
     * 上述另外一种写法
     */
    fun save1(user: Mono<User>): Mono<User>  = user.flatMap { usr ->
            userRepository.save(usr).onErrorResume {
                userRepository.findByUserName(usr.userName).flatMap {
                    userRepository.save(usr.copy(id = it.id))
                }
        }
    }


    fun deleteByUserName(userName: String) = userRepository.deleteByUserName(userName)

    fun findByUserName(userName: String) = userRepository.findByUserName(userName)

    fun findAll(): Flux<User> = userRepository.findAll()
}