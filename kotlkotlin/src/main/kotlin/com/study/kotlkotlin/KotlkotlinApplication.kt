package com.study.kotlkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class KotlkotlinApplication

fun main(args: Array<String>) {
    runApplication<KotlkotlinApplication>(*args)
}