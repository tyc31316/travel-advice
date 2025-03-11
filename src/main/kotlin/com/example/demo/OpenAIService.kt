package com.example.demo

import com.aallam.openai.client.OpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatResponseFormat
import com.aallam.openai.api.chat.JsonSchema
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.Json

class OpenAIService {

    companion object {
        val responseFormat: String = "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "      \"result\": {\n" +
                "        \"type\": \"array\",\n" +
                "        \"items\": {\n" +
                "          \"type\": \"object\",\n" +
                "          \"properties\": {\n" +
                "            \"month\": {\n" +
                "              \"type\": \"string\"\n" +
                "            },\n" +
                "            \"reason\": {\n" +
                "              \"type\": \"string\",\n" +
                "              \"enum\": [\n" +
                "                \"good weather\",\n" +
                "                \"less crowded\",\n" +
                "                \"festival\"\n" +
                "              ]\n" +
                "            }\n" +
                "          },\n" +
                "          \"required\": [\n" +
                "            \"month\",\n" +
                "            \"reason\"\n" +
                "          ],\n" +
                "          \"additionalProperties\": false\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"required\": [\n" +
                "      \"result\"\n" +
                "    ],\n" +
                "    \"additionalProperties\": false\n" +
                "  }"
    }

    suspend fun getTravelSuggestion(rawData: String): ChatCompletion {

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-4o-mini"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.User,
                    content = rawData
                )
            ),
            responseFormat = ChatResponseFormat(
                type = "json_schema",
                jsonSchema= JsonSchema(
                    schema = Json.decodeFromString(JsonObject.serializer(), responseFormat),
                    strict =  true,
                    name = "travel_advice"
                )
            )
        )

        val completion = openAI.chatCompletion(chatCompletionRequest)
        return completion
    }


    val openAI = OpenAI(
        token = "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",
    )

}