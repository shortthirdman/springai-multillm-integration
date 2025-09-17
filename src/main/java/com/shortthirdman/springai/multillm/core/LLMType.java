package com.shortthirdman.springai.multillm.core;

import lombok.Getter;

@Getter
public enum LLMType {

    OPENAI("openai"),
    OLLAMA("ollama"),
    GEMINI("gemini"),
    ANTHROPIC("anthropic"),
    VERTEX("vertexai"),
    MISTRAL("mistral");

    private final String value;

    LLMType(String value) {
        this.value = value;
    }

}
