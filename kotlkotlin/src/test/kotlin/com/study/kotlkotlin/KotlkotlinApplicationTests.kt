package com.study.kotlkotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class KotlkotlinApplicationTests {

    @Test
    fun contextLoads() {

        val fruits1 = listOf("apple", "banana", "kiwi", "cherry")

        fruits1
            .filter {
                println("checking the length of $it")
                it.length > 5
            }
            .map {
                println("mapping to the length of $it")
                "${it.length}"
            }
            .take(1);

        println("/////////////////////")

        val fruits2 = listOf("apple", "banana", "kiwi", "cherry")

        fruits2
            .asSequence()
            .filter {
                println("checking the length of $it")
                it.length > 5
            }
            .map {
                println("mapping to the length of $it")
                "${it.length}"
            }
            .take(1)
            .toList();
    }

    @Test
    fun varval() {

        val name: String = "kimeunbin"
        println("My name is $name")
    }

    @Test
    fun inheritance() {

        val name: String = "kimeunbin"
        println("My name is $name")
    }
}