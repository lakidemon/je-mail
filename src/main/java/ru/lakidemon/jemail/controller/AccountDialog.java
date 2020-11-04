package ru.lakidemon.jemail.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.domain.Account;
import ru.lakidemon.jemail.domain.ConnectionInfo;
import ru.lakidemon.jemail.domain.Credentials;
import ru.lakidemon.jemail.domain.Protocol;
import ru.lakidemon.jemail.service.AccountService;

@Component
@FxmlView
@RequiredArgsConstructor
public class AccountDialog {
    private final AccountService accountService;

    public TextField host;
    public Spinner<Integer> port;
    public PasswordField password;
    public TextField username;
    public CheckBox ssl;
    public AnchorPane root;

    @Getter
    private Account account;

    @FXML
    public void initialize() {
        port.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 65535, 993));
    }

    public void submitForm(MouseEvent mouseEvent) {
        this.account = toAccount();
        accountService.saveAccount(this.account);
        ((Stage) root.getScene().getWindow()).close();
    }

    private Account toAccount() {
        var account = new Account();
        account.setIncomingServer(new ConnectionInfo(null, host.getText(), port.getValue(), Protocol.IMAP, ssl.isSelected()));
        account.setCredentials(new Credentials(username.getText(), password.getText()));
        return account;
    }

}
