package com.ingo.seed.reactive

import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringCloudApplication
@EnableHystrix
@EnableTransactionManagement
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
