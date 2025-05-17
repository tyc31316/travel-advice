package com.example.demo.controller

import OpenAIApiResponse
import com.example.demo.service.OpenAIService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/travel")
class HelloController(
    val openAIService: OpenAIService,
) {
    @GetMapping("/suggestion/{country}")
    suspend fun getTravelSuggestion(
        @PathVariable country: String,
    ): String {
        val mapper = jacksonObjectMapper()

        val result = openAIService.getTravelSuggestion("Search the web and give me advice on the best travel time in $country")

        return mapper
            .readValue(
                result.choices[0].message.content,
                OpenAIApiResponse::class.java,
            ).result
            .toString()
    }

    @GetMapping("hello")
    fun getHello(): String {
        return "hello"
    }
}
