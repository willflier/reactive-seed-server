package com.ingo.seed.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class User(
    @Id// 注解属性id为ID
//    val id: String,
    val id: String = UUID.randomUUID().toString(),
    @Indexed(unique = true) // 注解属性username为索引，并且不能重复
    val userName: String,
    val phone: String = "",
    val email: String ="",
    val name: String = "",
    val birthday: Date?
)