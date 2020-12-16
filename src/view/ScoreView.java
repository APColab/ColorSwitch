package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.converter.NumberStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ScoreView extends Label
{
    public ScoreView()
    {
        setLayoutX(30);
        setLayoutY(20);
        try {
            setFont(Font.loadFont(new FileInputStream("src/menupages/resources/eroded.ttf"), 18));
        } catch (FileNotFoundException e) {
            setFont(new Font("Verdana", 18));
        }
        setTextFill(Color.WHITE);
        setPrefHeight(30);
        setPrefWidth(100);
    }
}
