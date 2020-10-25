package ru.lakidemon.jemail;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.lakidemon.jemail.launch.JFXBootstrap;

@SpringBootApplication
public class JEMailApplication {

    public static void main(String[] args) {
        Application.launch(JFXBootstrap.class, args);
    }

}
