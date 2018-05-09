package com.ingo.seed.reactive.repository

import com.ingo.seed.reactive.model.User
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class UserRepository(
    private val template: ReactiveMongoTemplate
) {
    fun save(user: User) = template.save(user)
    fun save(user: Mono<User>) = template.save(user)
    fun findAll() = template.findAll<User>()
    fun findByUserName(userName: String) = template.findOne<User>(Query(Criteria.where("userName").isEqualTo(userName)))
    fun deleteByUserName(userName: String) = template.remove(Query(where("userName").isEqualTo(userName)))

}