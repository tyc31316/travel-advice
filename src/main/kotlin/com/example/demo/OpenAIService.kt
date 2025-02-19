package com.example.demo

class OpenAIService {

    val openAI = OpenAI(apiKey)
    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        messages = listOf(
            ChatMessage(
                role = ChatRole.System,
                content = "You are a helpful assistant!"
            ),
            ChatMessage(
                role = ChatRole.User,
                content = "Hello!"
            )
        )
    )
    val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
    // or, as flow
    val completions: Flow<ChatCompletionChunk> = openAI.chatCompletions(chatCompletionRequest)
}