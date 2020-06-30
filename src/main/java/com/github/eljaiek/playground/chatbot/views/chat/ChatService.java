package com.github.eljaiek.playground.chatbot.views.chat;

import lombok.RequiredArgsConstructor;
import org.alicebot.ab.Chat;

@RequiredArgsConstructor
public class ChatService {

    private final Chat chatSession;

    public String answer(String message) {
        return chatSession.multisentenceRespond(message);
    }
}
