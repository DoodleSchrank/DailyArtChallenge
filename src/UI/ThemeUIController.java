package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ThemeUIController {
    private MainUIController _controller;
    @FXML
    private Label themeTimeLabel;

    ThemeUIController(MainUIController controller)
    {
        _controller = controller;
    }

    public void setMainController(MainUIController controller)
    {
        _controller = controller;
        System.out.println("Erfolgreich ThemeUI Gestartet!");
    }

    @FXML
    private void initialize()
    {
        themeTimeLabel.setText(_controller.getTimer() + " Min.");
    }

    @FXML
    public void buttonPressed(ActionEvent event) throws Exception
    {
        Button source = (Button) event.getSource();
        switch (source.getId()) {
            case "themeButtonLebewesen":
                _controller.setTheme(0);
                break;
            case "themeButtonItems":
                _controller.setTheme(1);
                break;
            case "themeButtonLandschaft":
                _controller.setTheme(2);
                break;
            case "themeButtonArchitektur":
                _controller.setTheme(3);
                break;
            case "themeButtonRandom":
                _controller.setTheme(4);
                break;
            case "themeButtonBack":
                _controller.setTimeUI();
                break;
        }
    }
}
