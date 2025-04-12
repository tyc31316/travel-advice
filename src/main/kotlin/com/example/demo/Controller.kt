package com.example.demo

import ApiResponse
import com.aallam.openai.api.chat.ChatCompletion
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@RestController
@RequestMapping("/travel")
class HelloController(
    val openAIService: OpenAIService
) {

    @GetMapping("/suggestion/{country}")
    fun getTravelSuggestion(@PathVariable country: String): String {

        var result: ChatCompletion? = null
        runBlocking {
            result = openAIService.getTravelSuggestion("Search the web and give me advice on the best travel time in $country")
        }


        val mapper = jacksonObjectMapper()
        return result?.let {
            mapper.readValue(
                it.choices[0].message.content,
                ApiResponse::class.java
            ).result.toString()
        } ?: throw  ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}