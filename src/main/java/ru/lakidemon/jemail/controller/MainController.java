package ru.lakidemon.jemail.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.domain.Account;
import ru.lakidemon.jemail.service.AccountService;
import ru.lakidemon.jemail.service.MailService;

import javax.mail.MessagingException;

@Component
@FxmlView
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final FxWeaver fxWeaver;
    private final MailService mailService;
    private final AccountService accountService;

    public ProgressBar mailProgress;
    public WebView messageView;
    private Account account;
    public VBox messagesContainer;

    @FXML
    public void initialize() throws MessagingException {
        requestAccount();
        populateMailList();
    }

    private void requestAccount() {
        this.account = accountService.getAccounts().stream().findFirst().orElseGet(() -> {
            var dialogBean = fxWeaver.load(AccountDialog.class);
            var view = dialogBean.getView().orElse(null);
            var controller = dialogBean.getController();
            var scene = new Scene((Parent) view);
            var stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            return controller.getAccount();
        });
    }

    private void populateMailList() throws MessagingException {
        var messages = mailService.getMessages(account);
        var task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = messages.size() - 1; i >= 0; i--) {
                    var message = messages.get(i);
                    var mailItemBean = fxWeaver.load(MailItem.class);
                    var controller = mailItemBean.getController();
                    var view = mailItemBean.getView().orElse(null);
                    controller.populateFields(message);
                    Platform.runLater(() -> messagesContainer.getChildren().add(view));
                    updateProgress(messages.size() - i, messages.size());
                }
                return null;
            }

            @Override
            protected void failed() {
                log.warn("Task failed successfully:", getException());
            }
        };
        mailProgress.progressProperty().bind(task.progressProperty());
        var thread = new Thread(task);
        thread.start();
    }
}
