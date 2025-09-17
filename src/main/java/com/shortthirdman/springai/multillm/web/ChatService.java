package com.shortthirdman.springai.multillm.web;

import com.shortthirdman.springai.multillm.core.LLMType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient openAIChatClient;
    private final ChatClient ollamaChatClient;
    private final ChatClient geminiChatClient;

    @Autowired
    public ChatService(OpenAiChatModel openAiChatModel,
                       OllamaChatModel ollamaChatModel,
                       @Qualifier("geminiChatClient") ChatClient geminiChatClient) {
        this.openAIChatClient = ChatClient.create(openAiChatModel);
        this.ollamaChatClient = ChatClient.create(ollamaChatModel);
        this.geminiChatClient = geminiChatClient;
    }

    public String chat(String llmName, String message) {
        var chatClient = getChatModel(LLMType.valueOf(llmName.toUpperCase()));
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    private ChatClient getChatModel(LLMType llmName) {
        return switch (llmName) {
            case OPENAI -> openAIChatClient;
            case OLLAMA -> ollamaChatClient;
            case GEMINI -> geminiChatClient;
        };
    }
}
