package UI;

import Backend.AlarmThread;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawingUIController {
    private MainUIController _controller;
    private boolean started = false;
    private String[] _themes;
    private Timer _mainTimer;
    private int _delay = 1000;
    private int _period = 1000;
    private int _timeSeconds;
    private int _origTimeSeconds;
    private Set<AlarmThread> _alarms;

    @FXML
    private GridPane themeGrid;
    @FXML
    private Label drawingTextTime;

    DrawingUIController(MainUIController controller, String[] themes)
    {
        _controller = controller;
        _themes = themes;
        _alarms = new HashSet<>();
    }
    @FXML
    public void initialize()
    {
        _origTimeSeconds = _timeSeconds = _controller.getTimer() * 60;
        for (String str : _themes) {
            themeGrid.add(new Label(str), Arrays.asList(_themes).indexOf(str), 0);
        }
        drawingTextTime.setText("Zeit: " + String.format("%02d", (int) (_timeSeconds/60)) + ":" + String.format("%02d", _timeSeconds%60));
    }
    @FXML
    public void buttonPressed(ActionEvent event) throws Exception
    {
        Button source = (Button) event.getSource();

        if (source.getId().equals("drawingButtonStartStop"))
        {
            if(!started)
            {
                source.setText("Stop");
                if(_origTimeSeconds > 600 && _timeSeconds > _origTimeSeconds / 2)
                {
                    AlarmThread alarm = new AlarmThread(_timeSeconds/2, "half");
                    alarm.start();
                    _alarms.add(alarm);
                }
                if(_origTimeSeconds >= 180 && _timeSeconds > 60)
                {
                    AlarmThread alarm = new AlarmThread(_origTimeSeconds - 60, "1min");
                    alarm.start();
                    _alarms.add(alarm);
                }
                _timeSeconds--;
                drawingTextTime.setText("Zeit: " + String.format("%02d", (int) (_timeSeconds/60)) + ":" + String.format("%02d", _timeSeconds%60));
                _mainTimer = new Timer();
                _mainTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() ->
                        {
                            _timeSeconds--;
                            drawingTextTime.setText("Zeit: " + String.format("%02d", (int) (_timeSeconds/60)) + ":" + String.format("%02d", _timeSeconds%60));
                            if(_timeSeconds <= 0)
                            {
                                ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand")).run();
                                _mainTimer.cancel();
                                started = false;
                                source.setDisable(true);
                            }
                        });
                    }
                }, _delay, _period);
            }
            else
            {
                _mainTimer.cancel();
                source.setText("Start");
                for (AlarmThread alarm : _alarms)
                {
                    if(alarm.isAlive())
                        alarm.interrupt();
                }
            }
            started = !started;
        }
        else if (source.getId().equals("drawingButtonBack"))
        {
            if (started)
            {
                for (AlarmThread alarm : _alarms)
                {
                    if(alarm.isAlive())
                        alarm.interrupt();
                }
            }
            _controller.setThemeUI();
        }
    }
}
