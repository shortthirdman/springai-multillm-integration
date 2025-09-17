package com.shortthirdman.springai.multillm.web;

import com.shortthirdman.springai.multillm.core.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<ChatResponse> chat(
            @RequestParam String message,
            @RequestParam String llm) {
        String response = chatService.chat(llm, message);
        var chat = ChatResponse.builder()
                .llm(llm)
                .originalMessage(message)
                .response(response)
                .build();
        return ResponseEntity.ok(chat);
    }
}
