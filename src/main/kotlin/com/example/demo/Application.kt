package com.example.demo

import com.example.demo.config.Configurations
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(Configurations::class)
class BooApplication

fun main(args: Array<String>) {
	runApplication<BooApplication>(*args)
}
