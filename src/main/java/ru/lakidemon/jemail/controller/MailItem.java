package ru.lakidemon.jemail.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import java.io.IOException;

@Component
@Scope("prototype")
@FxmlView
@RequiredArgsConstructor
public class MailItem {
    private final MainController mainController;
    public Label subject;
    public Label from;
    public Label text;

    private String messageText;

    public void populateFields(Message message) throws MessagingException, IOException {
        var content = message.getContent();
        var messageTextBuilder = new StringBuilder();
        var messagePreviewBuilder = new StringBuilder();
        if (content instanceof Multipart) {
            var multipartContent = (Multipart) content;
            for (int i = 0; i < multipartContent.getCount(); i++) {
                var part = multipartContent.getBodyPart(i);
                MimeType mimeType = MimeTypeUtils.parseMimeType(part.getContentType());
                (mimeType.getSubtype().equals("html") ? messageTextBuilder : messagePreviewBuilder).append(part.getContent());
            }
        } else {
            messageTextBuilder.append(content);
        }
        var subject = message.getSubject();
        var from = ((InternetAddress) message.getFrom()[0]).getAddress();
        this.messageText = messageTextBuilder.toString();
        Platform.runLater(() -> {
            this.subject.setText(subject);
            this.from.setText("From: " + from);
            this.text.setText(messagePreviewBuilder.toString());
        });
    }

    public void onMessageClicked(MouseEvent mouseEvent) {
        mainController.messageView.getEngine().loadContent(messageText);
    }
}
