package UI;

import Backend.ThemeGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainUIController {
    // JavaFX UI
    @FXML private TimeUIController timeUI;
    @FXML private ThemeUIController themeUI;
    @FXML private AnchorPane frame;

    // Fields for Time and Theme
    private int _time;
    private int _themeID;

    // onLoad()
    @FXML
    public void initialize() throws IOException
    {
        setTimeUI();
    }

    /**
     * Sets time for the timer in minutes and starts the ThemeUI
     */
    void setTimer(int time) throws IOException
    {
        _time = time;
        setThemeUI();
    }

    /**
     * Returns time for the timer in minutes
     * @return _time
     */
    int getTimer()
    {
        return _time;
    }

    /**
     * Sets the ThemeID
     * 0 = Living Creatures
     * 1 = Items
     * 2 = Landscape
     * 3 = Architeture
     * 4 = Random
     * @param themeID ID of the theme to be set, list above specifies the numbers
     */
    void setTheme(int themeID) throws IOException
    {
        _themeID = themeID;
        setDrawingUI();
    }
    private String[] getThemes() throws IOException
    {
        return ThemeGenerator.generate(3, _themeID);
    }

    /**
     * Sets TimeUI
     * @throws IOException if something goes wrong loading fxml
     */
    void setTimeUI() throws IOException
    {
        FXMLLoader timeLoader = new FXMLLoader();
        timeLoader.setLocation(getClass().getResource("/UI/TimeUI.fxml"));
        timeLoader.setController(new TimeUIController(this));
        frame.getChildren().clear();
        frame.getChildren().addAll((AnchorPane) timeLoader.load());
    }

    void setThemeUI() throws IOException
    {
        FXMLLoader themeLoader = new FXMLLoader();
        themeLoader.setLocation(getClass().getResource("/UI/ThemeUI.fxml"));
        themeLoader.setController(new ThemeUIController(this));
        frame.getChildren().clear();
        frame.getChildren().addAll((AnchorPane) themeLoader.load());
    }
    private void setDrawingUI() throws IOException
    {
        FXMLLoader drawingLoader = new FXMLLoader();
        drawingLoader.setLocation(getClass().getResource("/UI/DrawingUI.fxml"));
        drawingLoader.setController(new DrawingUIController(this, getThemes()));
        frame.getChildren().clear();
        frame.getChildren().addAll((AnchorPane) drawingLoader.load());
    }
}
