package com.example.demo

import com.example.demo.config.OpenAIConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OpenAIConfig::class)
class BooApplication

fun main(args: Array<String>) {
	runApplication<BooApplication>(*args)
}
