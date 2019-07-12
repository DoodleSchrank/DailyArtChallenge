package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

public class TimeUIController
{
    @FXML private Spinner customTimeSpinner;
    private MainUIController _controller;

    TimeUIController(MainUIController controller)
    {
        _controller = controller;
    }

    @FXML
    public void initialize()
    {
        customTimeSpinner.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(customTimeSpinner);
        });
    }

    @FXML
    public void buttonPressed(ActionEvent event) throws Exception
    {
        Button source = (Button) event.getSource();

        switch (source.getId())
        {
            case "timerButton30":
                _controller.setTimer(30);
                break;
            case "timerButton45":
                _controller.setTimer(45);
                break;
            case "timerButton60":
                _controller.setTimer(60);
                break;
            case "timerButton90":
                _controller.setTimer(90);
                break;
            case "timerButton120":
                _controller.setTimer(120);
                break;
            case "timerButton180":
                _controller.setTimer(180);
                break;
            default:
                if (customTimeSpinner.getValue() != null) _controller.setTimer((int) customTimeSpinner.getValue());
                else break;
        }
    }

    private <T> void commitEditorText(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }
}
