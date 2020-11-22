package menupages;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends Button
{
    private final String MY_FONT = "src/menupages/resources/Proxima_Nova_Alt_Bold.otf";
    private String idleButton;
    private String pressedButton;

    public GameButton(String st, String pathButtonIdle, String pathButtonPressed)
    {
        setText(st);
        idleButton = pathButtonIdle;
        pressedButton = pathButtonPressed;
        setFontForButton();
        setPrefWidth(190);
        setPrefHeight(50);
        setStyle(idleButton);
        initButtonListeners();
    }
    private void setPressedButton()
    {
        setPrefHeight(45);
        setStyle(pressedButton);
        setLayoutY(getLayoutY() + 4);
    }

    private void setIdleButton()
    {
        setPrefHeight(49);
        setStyle(idleButton);
        setLayoutY(getLayoutY() - 4);
    }

    private void setFontForButton()
    {
        try {
            setFont(Font.loadFont(new FileInputStream(MY_FONT), 19));
        }
        catch (FileNotFoundException e)
        {
            setFont(Font.font("Verdana", FontWeight.BOLD, 19));
        }
    }

    private void initButtonListeners()
    {
        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                Glow glow = new Glow();
                glow.setLevel(0);

                setPressedButton();
                setEffect(glow);
            }
        });

        setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                setIdleButton();
            }
        });

        setOnMouseEntered(mouseEvent ->
        {
            Glow glow = new Glow();
            glow.setLevel(0.5);
            /**Light.Spot ls = new Light.Spot();
            ls.setColor(Color.GREEN);
            ls.setX(70);
            ls.setY(55);
            ls.setZ(45);
            Lighting light = new Lighting();
            light.setLight(ls);
            //g.setLevel(0.7);**/
            setEffect(glow);
        });

        setOnMouseExited(mouseEvent -> setEffect(null));
    }
}

