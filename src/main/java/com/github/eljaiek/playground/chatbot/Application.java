package com.github.eljaiek.playground.chatbot;

import com.github.eljaiek.playground.chatbot.views.chat.ChatService;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Bot alice() {
        return new Bot(BotConfiguration.builder()
                .name("alice")
                .path("src/main/resources")
                .build()
        );
    }

    @Bean
    public Chat chatSession() {
        return new Chat(alice());
    }

    @Bean
    public ChatService chatService() {
        return new ChatService(chatSession());
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }
}
