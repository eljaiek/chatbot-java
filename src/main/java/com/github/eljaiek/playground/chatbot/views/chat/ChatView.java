package com.github.eljaiek.playground.chatbot.views.chat;

import com.github.eljaiek.playground.chatbot.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.val;
import org.vaadin.artur.Avataaar;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

import static com.github.eljaiek.playground.chatbot.views.chat.MessageList.MessageCommand;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Route(value = "chat", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Chat")
@CssImport("styles/views/chat/chat-view.css")
public class ChatView extends VerticalLayout {

    private final UI ui;
    private final MessageList messageList;
    private final TextField message;
    private final transient ChatService chatService;
    private final transient ScheduledExecutorService executorService;

    public ChatView(ChatService chatService, ScheduledExecutorService executorService) {
        this.chatService = chatService;
        this.executorService = executorService;
        message = new TextField();
        messageList = new MessageList();
        ui = UI.getCurrent();
        message.setPlaceholder("Enter a message...");
        message.setSizeFull();
        val send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);
        val inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");
        add(messageList, inputLayout);
        expand(messageList);
        setSizeFull();
    }

    private void sendMessage() {
        val userQuestion = MessageCommand.builder()
                .from("You")
                .avatar(new Avataaar("Name"))
                .text(message.getValue())
                .isCurrentUser(true)
                .build();
        messageList.addMessage(userQuestion);
        message.clear();
        executorService.schedule(() -> {
            val aliceAnswer = MessageCommand.builder()
                    .from("Alice")
                    .avatar(new Avataaar("Alice2"))
                    .text(chatService.answer(userQuestion.toString()))
                    .isCurrentUser(false)
                    .build();
            ui.access(() -> messageList.addMessage(aliceAnswer));
        }, ThreadLocalRandom.current().nextInt(100, 300), MILLISECONDS);
    }
}
