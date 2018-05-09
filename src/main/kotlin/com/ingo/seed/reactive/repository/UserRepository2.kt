package com.ingo.seed.reactive.repository

import com.ingo.seed.reactive.model.User
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

interface UserRepository2: ReactiveCrudRepository<User, String> {
    fun findByUserName(userName: String): Mono<User>
    fun deleteByUserName(userName: String): Mono<Long>
}