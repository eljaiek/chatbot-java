package com.github.eljaiek.playground.chatbot.views.chat;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.vaadin.artur.Avataaar;

import java.util.function.Consumer;

final class MessageList extends Div {

    public MessageList() {
        setClassName(getClass().getSimpleName());
    }

    public void addMessage(@NonNull MessageCommand command) {
        Span fromContainer = new Span(new Text(command.from));
        fromContainer.addClassName(getClass().getSimpleName() + "-name");
        Div textContainer = new Div(new Text(command.text));
        textContainer.addClassName(getClass().getSimpleName() + "-bubble");
        Div avatarContainer = new Div(command.avatar, fromContainer);
        avatarContainer.addClassName(getClass().getSimpleName() + "-avatar");
        Div line = new Div(avatarContainer, textContainer);
        line.addClassName(getClass().getSimpleName() + "-row");
        add(line);
        command.ifIsCurrentUser(v -> {
            line.addClassName(getClass().getSimpleName() + "-row-currentUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-currentUser");
        });
        line.getElement().callJsFunction("scrollIntoView");
    }

    public static class MessageCommand {
        private final String from;
        private final Avataaar avatar;
        @Getter
        private final String text;
        private final boolean isCurrentUser;

        @Builder
        public MessageCommand(String from, Avataaar avatar, String text, boolean isCurrentUser) {
            this.from = from;
            this.avatar = avatar;
            this.text = text;
            this.isCurrentUser = isCurrentUser;
        }

        private void ifIsCurrentUser(Consumer<Void> consumer) {

            if (isCurrentUser) {
                consumer.accept(null);
            }
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
