package com.shortthirdman.springai.multillm.core;

import lombok.Getter;

@Getter
public enum LLMType {

    OPENAI("openai"),
    OLLAMA("ollama"),
    GEMINI("gemini");

    private final String value;

    LLMType(String value) {
        this.value = value;
    }

}
