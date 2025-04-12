package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationProperties("openai")
@ConfigurationPropertiesScan
data class Configurations(
    val token: String,
)