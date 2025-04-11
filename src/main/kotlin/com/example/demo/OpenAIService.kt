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
import org.springframework.stereotype.Service

@Service
class OpenAIService(
    configurations: Configurations,
) {

    companion object {
        val responseFormat: String = "{" +
                "    \"type\": \"object\"," +
                "    \"properties\": {" +
                "      \"result\": {" +
                "        \"type\": \"array\"," +
                "        \"items\": {" +
                "          \"type\": \"object\"," +
                "          \"properties\": {" +
                "            \"month\": {" +
                "              \"type\": \"string\"" +
                "            }," +
                "            \"reason\": {" +
                "              \"type\": \"string\"," +
                "              \"enum\": [" +
                "                \"good weather\"," +
                "                \"less crowded\"," +
                "                \"festival\"" +
                "              ]" +
                "            }" +
                "          }," +
                "          \"required\": [" +
                "            \"month\"," +
                "            \"reason\"" +
                "          ]," +
                "          \"additionalProperties\": false" +
                "        }" +
                "      }" +
                "    }," +
                "    \"required\": [" +
                "      \"result\"" +
                "    ]," +
                "    \"additionalProperties\": false" +
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
        token = configurations.token,
    )
}