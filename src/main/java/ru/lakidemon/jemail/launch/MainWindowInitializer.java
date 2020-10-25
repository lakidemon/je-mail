package ru.lakidemon.jemail.launch;

import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.controller.MainController;

@Component
@RequiredArgsConstructor
public class MainWindowInitializer implements ApplicationListener<StageReadyEvent> {
    private final FxWeaver fxWeaver;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        var stage = event.getSource();
        Parent view = fxWeaver.loadView(MainController.class);
        var scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }
}
